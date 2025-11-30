package com.naho.umc9th.domain.store.controller;

import com.naho.umc9th.domain.common.apiPayload.ApiResponse;
import com.naho.umc9th.domain.common.apiPayload.code.GeneralSuccessCode;
import com.naho.umc9th.domain.common.validation.CheckPage;
import com.naho.umc9th.domain.store.dto.StoreResDTO;
import com.naho.umc9th.domain.store.service.StoreQueryService;
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
@RequestMapping("/stores")
@Tag(name = "Store API", description = "가게 관련 API")
public class StoreController {

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",
            description = "특정 가게의 미션 목록을 조회합니다. query String으로 page 번호를 주세요")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID, path variable입니다."),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1페이지 입니다.")
    })
    public ApiResponse<StoreResDTO.MissionListDTO> getMissions(
            @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, storeQueryService.getMissionList(storeId, page - 1));
    }
}
