package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private String name;
    private String email;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}