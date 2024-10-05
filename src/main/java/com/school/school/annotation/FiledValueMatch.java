package com.school.school.annotation;


import com.school.school.validation.FieldValueMatchValidation;
import com.school.school.validation.PasswordValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = FieldValueMatchValidation.class)
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface FiledValueMatch {


    String message() default "Fields values don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    String confirmField();

    @Target({TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FiledValueMatch[] value();
    }



}
