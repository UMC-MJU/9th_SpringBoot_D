package com.example.umc9th.controller;

import com.example.umc9th.domain.member.MemberMission;
import com.example.umc9th.dto.mission.ChallengeMissionRequest;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.SuccessCode;
import com.example.umc9th.service.MissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/challenge")
    public ResponseEntity<ApiResponse<MemberMission>> challengeMission(
        @Valid @RequestBody ChallengeMissionRequest request 
    ){
        MemberMission memberMission = missionService.challengeMission(request);
        return ResponseEntity.status(SuccessCode.MISSION201.getStatus())
            .body(ApiResponse.onSuccess(SuccessCode.MISSION201, memberMission));
    }
}
