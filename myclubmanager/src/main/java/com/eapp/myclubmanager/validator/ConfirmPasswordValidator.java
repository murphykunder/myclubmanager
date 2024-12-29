package com.eapp.myclubmanager.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    private String originalPassword;
    private String confirmPassword;
    private String message;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        this.originalPassword = constraintAnnotation.originalPassword();
        this.confirmPassword = constraintAnnotation.confirmedPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String originalPassword = (String) new BeanWrapperImpl(value).getPropertyValue(this.originalPassword);
        String confirmedPassword = (String) new BeanWrapperImpl(value).getPropertyValue(this.confirmPassword);

        boolean isValid = originalPassword.equals(confirmedPassword);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmedPassword)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
