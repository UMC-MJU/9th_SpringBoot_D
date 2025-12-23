package com.example.umc9th.global.annotation;

import com.example.umc9th.global.validator.MemberIdExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MemberIdExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMemberId {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당하는 유저 Id 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
