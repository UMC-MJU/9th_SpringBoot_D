package com.naho.umc9th.domain.member.dto;

import com.naho.umc9th.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDto {

    public record JoinDTO(
            String name,
            String email,
            String password,
            String phoneNumber,
            Gender gender,
            LocalDate birthday,
            String address,
            List<Long> preferCategory

    ){}
}
