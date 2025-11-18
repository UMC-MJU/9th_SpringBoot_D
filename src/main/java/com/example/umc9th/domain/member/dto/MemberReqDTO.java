package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotNull
            String email,
            @NotNull
            String phone,
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            String birth,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
