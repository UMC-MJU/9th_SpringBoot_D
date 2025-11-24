package com.naho.umc9th.domain.member.service.command;

import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;

public interface MemberCommandService {

    //회원가입
    MemberResDTO.JoinDTO signup(
            MemberReqDto.JoinDTO joinDTO
    );
}
