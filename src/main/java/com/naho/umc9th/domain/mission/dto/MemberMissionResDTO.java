package com.naho.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record CreateResultDTO(
            Long memberMissionId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MemberMissionPreViewDTO(
            Long memberMissionId,
            String storeName,
            String missionName,
            String missionContent,
            Integer point,
            String status,
            LocalDate createdAt
    ){}

    @Builder
    public record MemberMissionPreViewListDTO(
            List<MemberMissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}


}
