package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {

    // Entity → DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO → Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .nickname(dto.nickname())
                .gender(dto.gender())
                .birthday(dto.birthday())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .phoneNum(dto.phoneNum())
                .point(0)
                .status(null)
                .social_uid(null)
                .social_type(null)
                .build();
    }
}
