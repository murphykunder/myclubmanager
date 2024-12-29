package com.eapp.myclubmanager.validator;

import com.eapp.myclubmanager.swimmer.SwimmerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueSwimmerEmailValidator implements ConstraintValidator<UniqueSwimmerEmail, String> {

    private String message;
    private final SwimmerRepository swimmerRepository;

    public UniqueSwimmerEmailValidator(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public void initialize(UniqueSwimmerEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isValid = value != null && !swimmerRepository.findByEmail(value).isPresent();

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return isValid;

    }
}
