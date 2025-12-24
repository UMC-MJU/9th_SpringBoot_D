package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.query.MissionQueryService;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {

    private final MissionQueryService missionQueryService;
    private final StoreRepository storeRepository;

    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionsByStore(
            @RequestParam String storeName,
            @RequestParam @ValidPage Integer page
    ) {
        // store 존재 여부 확인
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // DTO 변환 + 성공 코드 포함
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_OK,
                missionQueryService.findMissionsByStore(store, page)
        );
    }
}

