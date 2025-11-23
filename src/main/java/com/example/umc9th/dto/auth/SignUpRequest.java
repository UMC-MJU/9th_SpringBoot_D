package com.example.umc9th.dto.auth;

import com.example.umc9th.domain.member.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import com.example.umc9th.global.annotation.ExistFood;
public record SignUpRequest(
        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(max = 30, message = "닉네임은 30자 이하로 입력해주세요.")
        String nickName,

        @NotNull(message = "성별을 선택해주세요.")
        Gender gender,

        @NotNull(message = "생년월일을 입력해주세요.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthday,

        @NotNull(message = "주소를 선택해주세요.")
        Long addressId,

        @NotEmpty(message = "선호 음식을 한 가지 이상 선택해주세요.")
        @ExistFood
        List<Long> preferredCategoryIds
) {
}
