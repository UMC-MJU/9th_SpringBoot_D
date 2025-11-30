package com.naho.umc9th.domain.member.controller;

import com.naho.umc9th.domain.common.apiPayload.ApiResponse;
import com.naho.umc9th.domain.common.apiPayload.code.GeneralSuccessCode;
import com.naho.umc9th.domain.common.validation.CheckPage;
import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.naho.umc9th.domain.member.service.command.MemberCommandService;
import com.naho.umc9th.domain.member.service.query.MemberQueryService;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.naho.umc9th.domain.mission.service.MemberMissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "Member API", description = "회원 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MemberMissionService memberMissionService;

    //회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDto.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    //내가 작성한 리뷰 목록 조회
    @GetMapping("{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 회원의 리뷰 목록을 조회합니다. Query String으로 page 번호를 주세요 (1부터 시작")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1페이지 입니다.")
    })
    public ApiResponse<MemberResDTO.ReviewPreViewListDTO> getReviewList(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, memberQueryService.getReviewList(memberId, page - 1));
    }

    //내가 진행 중인 미션 목록 조회
    @GetMapping("{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "진행 중인 미션들을 페이징하여 조회합니다. Query String으로 page 번호를 주세요 (1부터 시작")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1페이지 입니다.")
    })
    public ApiResponse<MemberMissionResDTO.MemberMissionPreViewListDTO> getMyOngoingMissions(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, memberMissionService.getMyOngoingMissions(memberId, page));
    }



}
