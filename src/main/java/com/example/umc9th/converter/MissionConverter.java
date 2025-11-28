package com.example.umc9th.converter;

import com.example.umc9th.domain.member.MemberMission;
import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.dto.MyMissionResponseDTO;
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

    public static MyMissionResponseDTO toMyMissionResponseDTO(MemberMission memberMission) {
        return MyMissionResponseDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionDescription(memberMission.getMission().getDescription())
                .rewardPoints(memberMission.getMission().getRewardPoints())
                .endDate(memberMission.getMission().getEndDate().toLocalDate())
                .status(memberMission.getStatus())
                .build();
    }
}
