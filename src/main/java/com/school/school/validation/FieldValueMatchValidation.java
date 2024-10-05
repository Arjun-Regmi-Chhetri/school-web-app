package com.school.school.validation;

import com.school.school.annotation.FiledValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldValueMatchValidation implements ConstraintValidator<FiledValueMatch, Object> {

    String field;
    String confirmField;

    @Override
    public void initialize(FiledValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.confirmField = constraintAnnotation.confirmField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(confirmField);
        if (fieldValue != null) {
            if(fieldValue.toString().startsWith("$2a")){
                return true;
            }else {
                return fieldValue.equals(fieldMatchValue);
            }
        } else {
            return fieldMatchValue == null;
        }

    }
}
