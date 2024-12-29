package com.eapp.myclubmanager.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @Email(message = "Invalid email format")
        @NotEmpty(message="Email is mandatory")
        @NotBlank(message="Email is mandatory")
        String email,
        @NotEmpty(message="Password is mandatory")
        @NotBlank(message="Password is mandatory")
        @Size(min=8, message="Password should be minimum 8 characters long")
        String password
) {
}
