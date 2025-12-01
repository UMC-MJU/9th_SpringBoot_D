package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MissionConverter {

    public static MissionResDTO.MissionListDTO toMissionListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.MissionListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionDTO toMissionDTO(
            Mission mission
    ) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                //.storeCategory(mission.getStore().getCategoryList())
                .comment(mission.getBody())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}