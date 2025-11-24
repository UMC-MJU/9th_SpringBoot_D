package com.naho.umc9th.domain.member.converter;

import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.enums.MemberStatus;
import com.naho.umc9th.domain.member.enums.Provider;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDto.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .birthday(dto.birthday())
                .address(dto.address())
                .gender(dto.gender())

                .status(MemberStatus.ACTIVE)
                .point(0)
                .provider(Provider.GOOGLE)
                .providerId("temp_provider_id")
                .build();
    }
}
