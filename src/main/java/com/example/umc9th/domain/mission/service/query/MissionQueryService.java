package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.enums.MissionState;

public interface MissionQueryService {

    MissionResDTO.MissionListDTO findMissionsByStoreId(Long storeId, Integer page);
    MissionResDTO.MissionListDTO findMissionsByMemberIdAndState(Long memberId, MissionState state, Integer page);
}
