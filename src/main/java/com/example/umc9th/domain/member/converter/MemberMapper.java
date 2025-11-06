package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "memberId", source = "id")
    MemberDto toDto(Member member);

    @Mapping(target = "id", source = "memberId")
    Member toEntity(MemberDto memberDto);
}