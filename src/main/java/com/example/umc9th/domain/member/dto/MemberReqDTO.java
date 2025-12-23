package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotNull(message = "이메일은 필수 입력 값입니다.")
            String email,
            @NotBlank(message = "비밀번호는 빈 칸이 될 수 없습니다.")
            String password,
            @NotNull(message = "전화번호는 필수 입력 값입니다.")
            @Pattern(regexp = "01(?:0|1|[6~9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
            String phone,
            @NotBlank(message = "이름은 빈 칸이 될 수 없습니다.")
            @NotNull(message = "이름은 필수 입력 값입니다.")
            String name,
            @NotNull(message = "성별은 필수 입력 값입니다.")
            Gender gender,
            @NotNull(message = "생년월일은 필수 입력 값입니다.")
            String birth,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
