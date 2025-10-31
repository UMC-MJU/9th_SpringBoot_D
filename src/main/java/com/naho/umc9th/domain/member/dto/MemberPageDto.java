package com.naho.umc9th.domain.member.dto;

public record MemberPageDto(
        String email,
        String nickname,
        String phoneNumber,
        Integer point
) {
}
