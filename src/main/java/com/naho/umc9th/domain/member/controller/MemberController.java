package com.naho.umc9th.domain.member.controller;

import com.naho.umc9th.domain.common.apiPayload.ApiResponse;
import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.naho.umc9th.domain.member.service.command.MemberCommandService;
import com.naho.umc9th.domain.member.service.command.MemberCommandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    //회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDto.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }
}
