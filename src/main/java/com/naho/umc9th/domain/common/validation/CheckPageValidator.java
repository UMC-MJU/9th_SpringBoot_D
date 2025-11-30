package com.naho.umc9th.domain.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context){
        if(value == null){
            return true;   //null 체크는 @NotNull 등 다른 어노테이션에 위임
        }

        if(value <= 0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("페이지 번호는 1 이상이어야 합니다.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }


}
