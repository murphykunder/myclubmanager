package com.eapp.myclubmanager.auth;

import com.eapp.myclubmanager.validator.ConfirmPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@ConfirmPassword(originalPassword = "password", confirmedPassword = "confirmPassword")
public record RegistrationRequest(

        @NotEmpty(message = "Firstname is required")
        @NotBlank(message = "Firstname is required")
        @Size(min = 2, message = "Firstname should have min 2 characters")
        String firstName,
        @NotEmpty(message = "Lastname is required")
        @NotBlank(message = "Lastname is required")
        @Size(min = 2, message = "Lastname should have min 2 characters")
        String lastName,
        @NotEmpty(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String emailId,
        @NotEmpty(message = "Password is required")
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 12, message = "Password should be between 8 - 12 characters")
        String password,
        @NotEmpty(message = "Confirm Password is required")
        @NotBlank(message = "Confirm Password is required")
        @Size(min = 8, max = 12, message = "Password should be between 8 - 12 characters")
        String confirmPassword
) {
}
