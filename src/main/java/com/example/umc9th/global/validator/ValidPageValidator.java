package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidPageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = value != null && value >= 1;

        if (!isValid) {
            // 디폴트 메시지 비활성화
            context.disableDefaultConstraintViolation();
            // 새로운 메시지로 덮어쓰기
            context.buildConstraintViolationWithTemplate("페이지 번호는 1 이상이어야 합니다.")
                    .addConstraintViolation();
        }

        return isValid;
    }
}