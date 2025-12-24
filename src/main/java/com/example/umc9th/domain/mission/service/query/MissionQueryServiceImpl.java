package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.MissionPreviewListDTO findMissionsByStore(Store store, int page) {
        // page 1 이상 보장, 한 페이지 10개
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // Mission 목록 조회
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);

        // DTO 변환
        return MissionConverter.toMissionPreviewListDTO(result);
    }
}
