package com.eapp.myclubmanager.validator;

import com.eapp.myclubmanager.trainer.TrainerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueTrainerEmailValidator implements ConstraintValidator<UniqueTrainerEmail, String> {

    private String message;
    private final TrainerRepository trainerRepository;

    public UniqueTrainerEmailValidator(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public void initialize(UniqueTrainerEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = value != null && !trainerRepository.findByEmail(value).isPresent();

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
