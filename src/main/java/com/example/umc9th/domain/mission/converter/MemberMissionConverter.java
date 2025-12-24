package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

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

    // 진행중 미션 목록 DTO 변환
    public static MemberMissionResDTO.ChallengeListDTO toChallengeListDTO(Page<MemberMission> page) {
        return MemberMissionResDTO.ChallengeListDTO.builder()
                .missionList(page.getContent().stream()
                        .map(MemberMissionConverter::toChallengeDTO)
                        .collect(Collectors.toList()))
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}