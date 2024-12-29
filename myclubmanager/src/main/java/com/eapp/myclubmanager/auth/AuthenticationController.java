package com.eapp.myclubmanager.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final HttpSession httpSession;
    @Value("${application.security.jwt.cookie-expiry}")
    private int cookieExpiry;

    public AuthenticationController(AuthenticationService authenticationService, HttpSession httpSession) {
        this.authenticationService = authenticationService;
        this.httpSession = httpSession;
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
//        System.out.println(httpSession.);
        return ResponseEntity.ok("Authentication controller is working");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/activate-account")
    public ResponseEntity<?> activateAccount(@RequestParam String token) throws MessagingException {
        return ResponseEntity.ok(authenticationService.activateAccount(token));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request, HttpServletResponse response){
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        ResponseCookie responseCookie = ResponseCookie.from("accessToken", authenticationResponse.getJwtToken())
                .httpOnly(true)
                .secure(false)// TODO change to true
                .path("/")
                .maxAge(cookieExpiry) // TODO need to check how it behaves on expiry
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
