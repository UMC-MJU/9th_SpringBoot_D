package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.MissionResDTO;

public interface MissionQueryService {

    MissionResDTO.MissionListDTO findMissionsByStoreId(Long storeId, Integer page);

}
