package com.eapp.myclubmanager.config;

import com.eapp.myclubmanager.role.Role;
import com.eapp.myclubmanager.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class BeansConfig {

	private final UserDetailsService userDetailsService;

	// :* means if we dont have the property application.cors.origins available then use all (*). * value is not recomended
//	@Value("${application.cors.origins:*}")
//	private List<String> allowedOrigins;

    public BeansConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {

		return (args) -> {
			// insert data for role table
			if(roleRepository.findByName("ADMIN").isEmpty()) {
				roleRepository.save(
						new Role.Builder()
                                .setName("ADMIN")
                                .createRole()
				);
			}
		};
	}


	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public ApplicationAuditAware auditAware(){
		return new ApplicationAuditAware();
	}
//
//	@Bean
//	public CorsFilter corsFilter(){
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration config = new CorsConfiguration();
//		// you can externalize this config to application.yml files
//		config.setAllowedOrigins(allowedOrigins);
//		config.setAllowedHeaders(Arrays.asList(
//				HttpHeaders.ORIGIN,
//				HttpHeaders.CONTENT_TYPE,
//				HttpHeaders.ACCEPT,
//				HttpHeaders.AUTHORIZATION
//		));
//		config.setAllowedMethods(Arrays.asList(
//				HttpMethod.GET.name(),
//				HttpMethod.POST.name(),
//				HttpMethod.PUT.name(),
//				HttpMethod.PATCH.name(),
//				HttpMethod.DELETE.name()
//		));
//		// register the CORS configuration for all resources/routes
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}

}
