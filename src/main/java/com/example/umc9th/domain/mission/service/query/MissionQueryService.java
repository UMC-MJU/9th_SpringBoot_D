package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.store.entity.Store;

public interface MissionQueryService {
    MissionResDTO.MissionPreviewListDTO findMissionsByStore(Store store, int page);
}