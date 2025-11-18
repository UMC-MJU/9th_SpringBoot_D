package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.global.entity.Address;

import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String email,
            String phone,
            String name,
            Gender gender,
            Integer point,
            String birth,
            List<Long> preferCategory
    ){}
}
