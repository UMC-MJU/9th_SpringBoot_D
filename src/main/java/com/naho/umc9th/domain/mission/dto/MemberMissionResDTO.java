package com.naho.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    public record CreateResultDTO(
            Long memberMissionId,
            LocalDateTime createdAt
    ){}
}
