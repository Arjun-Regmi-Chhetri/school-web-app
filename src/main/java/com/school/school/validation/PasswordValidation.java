package com.school.school.validation;


import com.school.school.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidation implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;


    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = Arrays.asList("password", "01234567", "12345678", "123456789", "1234567890");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value != null && !weakPasswords.contains(value);
    }
}
