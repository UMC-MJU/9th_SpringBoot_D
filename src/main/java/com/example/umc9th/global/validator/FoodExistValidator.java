package com.example.umc9th.global.validator;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import com.example.umc9th.global.annotation.ExistFood;
import com.example.umc9th.repository.store.CategoryRepository;
import com.example.umc9th.global.apiPayload.code.ErrorCode;
@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFood, List<Long>>{

    private final CategoryRepository categoryRepository;
    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context){
        boolean isValid = values.stream()
                .allMatch(value -> categoryRepository.existsById(value));

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorCode.CATEGORY001.getMessage()).addConstraintViolation();
        }
        return isValid;
    }
}
