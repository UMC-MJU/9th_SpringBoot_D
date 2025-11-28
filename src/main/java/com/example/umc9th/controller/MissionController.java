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
import com.example.umc9th.dto.MyMissionResponseDTO;
import com.example.umc9th.global.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/challenge")
    public ResponseEntity<ApiResponse<MemberMission>> challengeMission(
            @Valid @RequestBody ChallengeMissionRequest request) {
        MemberMission memberMission = missionService.challengeMission(request);
        return ResponseEntity.status(SuccessCode.MISSION201.getStatus())
                .body(ApiResponse.onSuccess(SuccessCode.MISSION201, memberMission));
    }

    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<Page<MyMissionResponseDTO>>> getMyChallengingMissions(
            @CheckPage @RequestParam(name = "page") Integer page,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        // TODO: 인증 구현 후 memberId 동적 할당
        Long memberId = 1L;
        pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MyMissionResponseDTO> missions = missionService
                .getMyChallengingMissions(memberId, pageable);
        return ResponseEntity
                .ok(ApiResponse.onSuccess(SuccessCode.MISSION200, missions));
    }

    @Operation(summary = "미션 완료 API", description = "진행중인 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION001", description = "존재하지 않는 미션입니다.", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
    })
    @PatchMapping("/{memberMissionId}/complete")
    public ResponseEntity<ApiResponse<Void>> completeMission(
            @PathVariable(name = "memberMissionId") Long memberMissionId) {
        missionService.completeMission(memberMissionId);
        return ResponseEntity.status(SuccessCode.MISSION200.getStatus())
                .body(ApiResponse.onSuccess(SuccessCode.MISSION200, null));
    }

    @Operation(summary = "내가 완료한 미션 목록 조회 API", description = "내가 완료한 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = com.example.umc9th.global.apiPayload.ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    @GetMapping("/complete")
    public ResponseEntity<ApiResponse<Page<MyMissionResponseDTO>>> getCompletedMissions(
            @CheckPage @RequestParam(name = "page") Integer page,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        // TODO: 인증 구현 후 memberId 동적 할당
        Long memberId = 1L;
        pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MyMissionResponseDTO> missions = missionService
                .getCompletedMissions(memberId, pageable);
        return ResponseEntity
                .ok(ApiResponse.onSuccess(SuccessCode.MISSION200, missions));
    }
}
