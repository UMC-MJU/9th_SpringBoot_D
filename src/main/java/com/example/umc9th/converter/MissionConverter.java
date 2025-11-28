package com.example.umc9th.converter;

import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.dto.StoreMissionResponseDTO;

public class MissionConverter {

    public static StoreMissionResponseDTO toStoreMissionResponseDTO(Mission mission) {
        return StoreMissionResponseDTO.builder()
                .missionId(mission.getId())
                .description(mission.getDescription())
                .rewardPoints(mission.getRewardPoints())
                .endDate(mission.getEndDate().toLocalDate())
                .build();
    }
}
