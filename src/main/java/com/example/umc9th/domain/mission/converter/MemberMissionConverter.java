package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MemberMissionConverter {

    // 미션 도전 후 DTO 변환
    public static MemberMissionResDTO.ChallengeDTO toChallengeDTO(MemberMission memberMission) {
        return MemberMissionResDTO.ChallengeDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeId(memberMission.getMission().getStore().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .deadline(memberMission.getMission().getDeadline())
                .point(memberMission.getMission().getPoint())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

}