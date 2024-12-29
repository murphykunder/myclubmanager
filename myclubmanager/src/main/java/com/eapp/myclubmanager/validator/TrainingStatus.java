package com.eapp.myclubmanager.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrainingStatusValidator.class)
public @interface TrainingStatus {

    String message() default "Invalid training status provided.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target(TYPE)
    @Retention(RUNTIME)
    public @interface List {
        TrainingStatus[] value();
    }

}
