package com.eapp.myclubmanager.auth;

import com.eapp.myclubmanager.email.EmailService;
import com.eapp.myclubmanager.email.EmailTemplateName;
import com.eapp.myclubmanager.exception.OperationNotPermittedException;
import com.eapp.myclubmanager.role.RoleRepository;
import com.eapp.myclubmanager.security.JwtService;
import com.eapp.myclubmanager.user.Token;
import com.eapp.myclubmanager.user.TokenRepository;
import com.eapp.myclubmanager.user.User;
import com.eapp.myclubmanager.user.UserRepository;
import io.jsonwebtoken.lang.Objects;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Value("${application.mailing.activation-url}")
    private String activationUrl;
    @Value("${application.security.activation-token.length}")
    private String activationTokenLength;
    @Value("${application.security.activation-token.valid-duration}")
    private String activationTokenValidDuration;

    public AuthenticationService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, EmailService emailService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public void register(RegistrationRequest request) throws MessagingException {

        // check if role initialized
        var adminRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new OperationNotPermittedException("Role ADMIN is not initialized"));

        //check if account already exists
        var userExists = userRepository.findByEmailId(request.emailId()).isPresent();
        if(userExists)
            throw new OperationNotPermittedException("Email address already exists. Please try to login");

        var newUser = new User.Builder()
                .setFirstname(request.firstName())
                .setLastname(request.lastName())
                .setPassword(passwordEncoder.encode(request.password()))
                .setEmailId(request.emailId())
                .setAccountLocked(false)
                .setEnabled(false)
                .setRoles(List.of(adminRole))
                .createUser();
        userRepository.save(newUser);

        // send validation mail with activation token
        sendValidationMail(newUser);

    }

    @Transactional
    public void sendValidationMail(User user) throws MessagingException {

        String activationToken = generateActivationToken(user);
        emailService.sendMail(
                user.getEmailId(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                activationToken,
                "Account Activation"
        );

    }

    private String generateActivationToken(User user) {
        String newToken = generateActivationToken(Integer.parseInt(activationTokenLength));
        var token = new Token.Builder()
                .setToken(newToken)
                .setCreatedAt(LocalDateTime.now())
                .setExpiresAt(LocalDateTime.now().plusMinutes(Long.parseLong(activationTokenValidDuration)))
                .setUser(user)
                .createToken();
        tokenRepository.save(token);
        return newToken;
    }


    private String generateActivationToken(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for(int i = 0; i < length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

    @Transactional
    public String activateAccount(String token) throws MessagingException {

        //check if token exists
        Token savedtoken = tokenRepository.findByToken(token).orElseThrow(() -> new OperationNotPermittedException("Invalid code provided. Please try again"));

        // check if token has expired
        if(LocalDateTime.now().isAfter(savedtoken.getExpiresAt())){
            sendValidationMail(savedtoken.getUser()); // send a new token
            return "Activation code has expired. A new code has been sent to the same email address";
//            throw new OperationNotPermittedException("Activation code has expired. A new code has been sent to the same email address");
        }

        // check if the token has already been validated previously
        if(!Objects.isEmpty(savedtoken.getValidatedAt())){
            throw new OperationNotPermittedException("You have already validated your account with this code. Please try to login");
        }

        // check if the user mentioned in token exists in user table
        var user = userRepository.findById(savedtoken.getUser().getId()).orElseThrow(() -> new OperationNotPermittedException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);

        // validate the token
        savedtoken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedtoken);

        return "Account activated successfully";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        // check if account exists
        userRepository.findByEmailId(request.email()).orElseThrow(() -> new UsernameNotFoundException("Account does not exists. Please contact the admin."));

        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = (User)auth.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", user.getFullName());

        String jwtToken = jwtService.generateToken(claims, user);

        return new AuthenticationResponse.Builder().setJwtToken(jwtToken).createAuthenticationResponse();
    }
}
