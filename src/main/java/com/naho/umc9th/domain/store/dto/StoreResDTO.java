package com.naho.umc9th.domain.store.dto;

import lombok.Builder;

import java.util.List;

public class StoreResDTO {

    // 미션 목록(Record)
    @Builder
    public record MissionListDTO(
            List<MissionDTO> missionList,
            Integer listSize,
            Integer totalpage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    // 미션 단건 정보(Record)
    @Builder
    public record MissionDTO(
            Long missionId,
            String name,
            String content,
            Integer point
    ){}


}
