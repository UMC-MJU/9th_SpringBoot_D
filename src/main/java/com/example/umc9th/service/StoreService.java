package com.example.umc9th.service;

import com.example.umc9th.converter.MissionConverter;
import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.dto.StoreMissionResponseDTO;
import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;
import com.example.umc9th.repository.store.MissionRepository;
import com.example.umc9th.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public Page<StoreMissionResponseDTO> getMissions(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.STORE001));

        Page<Mission> missionPage = missionRepository.findAllByStoreId(storeId, pageable);

        List<StoreMissionResponseDTO> missionResponses = missionPage.getContent().stream()
                .map(MissionConverter::toStoreMissionResponseDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(
                missionResponses,
                pageable,
                missionPage.getTotalElements());
    }
}
