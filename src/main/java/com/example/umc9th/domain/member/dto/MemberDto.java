package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record MemberDto (
    Long memberId,
    String email,
    String phone,
    String name,
    Gender gender,
    Integer point,
    String birth,
    SocialType socialType
){

    public static MemberDto fromEntity(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getPhone(),
                member.getName(),
                member.getGender(),
                member.getPoint(),
                member.getBirth(),
                member.getSocialType()
        );
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}