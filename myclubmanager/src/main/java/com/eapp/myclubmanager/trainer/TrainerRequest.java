package com.eapp.myclubmanager.trainer;

import com.eapp.myclubmanager.validator.PhoneNumber;
import com.eapp.myclubmanager.validator.UniqueTrainerEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record TrainerRequest(

        Long id,
        @NotEmpty(message = "Trainer firstname cannot be ")
        @NotBlank(message = "Trainer firstname cannot be ")
        String firstname,
        @NotEmpty(message = "Trainer lastname cannot be ")
        @NotBlank(message = "Trainer lastname cannot be ")
        String lastname,
        @NotEmpty(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @UniqueTrainerEmail(message="Trainer with this email id already exists")
        String email,
        @NotEmpty(message = "Phone Number is required")
        @NotBlank(message = "Phone Number is required")
        @PhoneNumber(message = "Invalid phone number provided")
        String phoneNumber
) {
}
