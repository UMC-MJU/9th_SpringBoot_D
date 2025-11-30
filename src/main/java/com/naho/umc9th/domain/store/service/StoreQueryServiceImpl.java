package com.naho.umc9th.domain.store.service;

import com.naho.umc9th.domain.mission.entity.Mission;
import com.naho.umc9th.domain.mission.repository.MissionRepository;
import com.naho.umc9th.domain.store.converter.StoreConverter;
import com.naho.umc9th.domain.store.dto.StoreResDTO;
import com.naho.umc9th.domain.store.entity.Store;
import com.naho.umc9th.domain.store.exception.StoreErrorcode;
import com.naho.umc9th.domain.store.exception.StoreException;
import com.naho.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;


    @Override
    public StoreResDTO.MissionListDTO getMissionList(Long storeId, Integer page) {

        // 1. 가게 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorcode.NOT_FOUND));

        // 2. 미션 목록 조회
        Page<Mission> storeMission = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        // 3. 변환 및 반환
        return StoreConverter.toMissionListDTO(storeMission);

    }
}
