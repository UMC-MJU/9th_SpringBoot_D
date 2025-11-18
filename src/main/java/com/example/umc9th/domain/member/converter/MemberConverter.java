package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .email(dto.email())
                .phone(dto.phone())
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .build();
    }
}