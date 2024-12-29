package com.eapp.myclubmanager.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
public @interface ConfirmPassword {

    String message() default "The passwords don't match";

    String originalPassword();

    String confirmedPassword();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target(TYPE)
    @Retention(RUNTIME)
    public @interface List {
        ConfirmPassword[] value();
    }

}
