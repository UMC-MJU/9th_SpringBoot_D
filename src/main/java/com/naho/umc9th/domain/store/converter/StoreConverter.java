package com.naho.umc9th.domain.store.converter;

import com.naho.umc9th.domain.mission.entity.Mission;
import com.naho.umc9th.domain.store.dto.StoreResDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResDTO.MissionDTO toMissionDTO(Mission mission){
        return StoreResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .name(mission.getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .build();
    }

    public static StoreResDTO.MissionListDTO toMissionListDTO(Page<Mission> missionList){

        List<StoreResDTO.MissionDTO> missionDTOList = missionList.stream()
                .map(StoreConverter::toMissionDTO)
                .collect(Collectors.toList());

        return StoreResDTO.MissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalpage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionDTOList.size())
                .missionList(missionDTOList)
                .build();
    }
}
