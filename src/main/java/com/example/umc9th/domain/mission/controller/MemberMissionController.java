package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/members/{memberId}/missions/{missionId}")
    public ApiResponse<MemberMissionResDTO.ChallengeDTO> challengeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId
    ) {
        MemberMission memberMission =
                memberMissionCommandService.challengeMission(memberId, missionId);

        return ApiResponse.onSuccess(
                MissionSuccessCode.CHALLENGED,
                MemberMissionConverter.toChallengeDTO(memberMission)
        );
    }
}
