package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.code.ErrorCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value <= 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.PAGE_NOT_VALID.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
