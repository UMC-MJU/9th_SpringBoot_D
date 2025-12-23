package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionResDTO;
import com.example.umc9th.domain.mission.enums.MissionState;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.command.MissionCommandService;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/user/{memberId}/missions/{missionId}")
    public ApiResponse<MemberMissionResDTO.AddMemberMissionDTO> progressMission(
            @RequestBody @Valid MemberMissionReqDTO.AddMemberMissionDTO dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, missionCommandService.progressMission(dto));
    }

    @GetMapping("/store/{storeId}/missions/")
    @Override
    public ApiResponse<MissionResDTO.MissionListDTO>  getMissionsByStoreId(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findMissionsByStoreId(storeId, page));
    }

    @GetMapping("/user/{memberId}/missions/")
    @Override
    public ApiResponse<MissionResDTO.MissionListDTO> getMissionsByMemberIdAndState(
            @PathVariable Long memberId,
            @RequestParam String state,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        MissionState missionState;
        try {
            missionState = MissionState.valueOf(state);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("미션 상태 변환에 실패했습니다.", e); // 굳이 출력이 나오진 않음
        }
        MissionSuccessCode code = MissionSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, missionQueryService.findMissionsByMemberIdAndState(memberId, missionState, page));
    }

}
