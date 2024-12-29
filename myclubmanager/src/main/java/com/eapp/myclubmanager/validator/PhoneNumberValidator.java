package com.eapp.myclubmanager.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private String message;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid = false;
        try {
            Phonenumber.PhoneNumber phonenumber = phoneNumberUtil.parse(value, "FR");
            isValid = phoneNumberUtil.isValidNumber(phonenumber);
        } catch (NumberParseException e) {
            logger.error(e.getMessage());
        }

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
