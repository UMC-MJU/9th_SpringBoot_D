package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.mapping.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/user/{memberId}/missions/{missionId}")
    public ApiResponse<MemberMissionResDTO.AddMemberMissionDTO> progressMission(
            @RequestBody @Valid MemberMissionReqDTO.AddMemberMissionDTO dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionCommandService.progressMission(dto));
    }
}
