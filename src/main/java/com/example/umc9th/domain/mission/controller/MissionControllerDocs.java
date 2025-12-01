package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.global.annotation.ExistStoreId;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;

public interface MissionControllerDocs {
    @Operation(
            summary = "상점에 미션 목록 조회 API",
            description = "특정 상점의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionListDTO> getMissionsByStoreId(
            @ExistStoreId
            Long storeId,
            @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다.")
            Integer page
    );
}
