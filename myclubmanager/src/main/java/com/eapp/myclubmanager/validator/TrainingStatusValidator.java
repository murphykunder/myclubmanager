package com.eapp.myclubmanager.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class TrainingStatusValidator implements ConstraintValidator<TrainingStatus, String> {

    private String message;
    private com.eapp.myclubmanager.swimmer.TrainingStatus[] trainingStatus;

    @Override
    public void initialize(TrainingStatus constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.trainingStatus = com.eapp.myclubmanager.swimmer.TrainingStatus.values();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isValid = value != null && Arrays.stream(this.trainingStatus).anyMatch(status -> status.getCode().equals(value));

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return isValid;

    }
}
