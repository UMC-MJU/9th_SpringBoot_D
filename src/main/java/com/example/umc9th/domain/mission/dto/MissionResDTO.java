package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.store.entity.Category;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record MissionListDTO(
            List<MissionResDTO.MissionDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
    @Builder
    public record MissionDTO(
        Long missionId,
        Long storeId,
        String storeName,
        //List<Category> storeCategory,
        String comment,
        Integer point,
        LocalDateTime deadline
    ){}
}
