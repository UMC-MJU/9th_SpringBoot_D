package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Enum 클래스의 모든 상수를 순회하며 입력 값과 비교
        return Arrays.stream(this.enumClass.getEnumConstants())
                .anyMatch(enumValue -> enumValue.name().equals(value));
    }
}