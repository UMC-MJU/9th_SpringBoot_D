package com.naho.umc9th.domain.mission.controller;

import com.naho.umc9th.domain.common.apiPayload.ApiResponse;
import com.naho.umc9th.domain.common.apiPayload.code.GeneralSuccessCode;
import com.naho.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.naho.umc9th.domain.mission.service.MemberMissionCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/in-progress")
    public ApiResponse<MemberMissionResDTO.CreateResultDTO> joinMission(
            @RequestBody @Valid MemberMissionReqDTO.CreateDTO request
    ){
        MemberMissionResDTO.CreateResultDTO result = memberMissionCommandService.createMemberMission(request);
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, result);
    }
}
