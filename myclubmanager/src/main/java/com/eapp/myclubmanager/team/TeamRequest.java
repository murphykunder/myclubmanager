package com.eapp.myclubmanager.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record TeamRequest(

        Long id,
        @NotBlank(message = "Team name is required")
        @NotEmpty(message = "Team name is required")
        String name
) {
}
