package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Address;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(

            @NotBlank(message = "이름은 필수 입력값입니다.")
            String name,

            @NotBlank(message = "이메일은 필수 입력값입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "닉네임은 필수 입력값입니다.")
            String nickname,

            @NotNull(message = "성별은 필수 입력값입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수 입력값입니다.")
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birthday,

            @NotNull(message = "주소는 필수 입력값입니다.")
            Address address,

            @NotBlank(message = "전화번호는 필수 입력값입니다.")
            String phoneNum,

            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            String password,

            @NotBlank(message = "상세 주소는 필수 입력값입니다.")
            String specAddress,

            @ExistFoods
            @NotNull(message = "선호 음식 카테고리는 필수입니다.")
            @Size(min = 1, message = "선호 음식은 최소 1개 이상 선택해야 합니다.")
            List<Long> preferCategory

    ) {}
}