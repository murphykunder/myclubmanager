package com.eapp.myclubmanager.swimmer;

import com.eapp.myclubmanager.validator.TrainingStatus;
import com.eapp.myclubmanager.validator.UniqueSwimmerEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record SwimmerRequest(

        Long id,
        @NotEmpty(message = "Firstname is required")
        @NotBlank(message = "Firstname is required")
        String firstname,
        @NotEmpty(message = "Lastname is required")
        @NotBlank(message = "Lastname is required")
        String lastname,
        @NotEmpty(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @UniqueSwimmerEmail(message="Swimmer with this email id already exists")
        String email,
        @NotEmpty(message = "Training status is required")
        @NotBlank(message = "Training status is required")
        @TrainingStatus(message="Invalid training status provided")
        String trainingStatus,
        Long trainerId
) {
}
