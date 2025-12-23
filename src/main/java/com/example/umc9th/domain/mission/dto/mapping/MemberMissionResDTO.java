package com.example.umc9th.domain.mission.dto.mapping;

import com.example.umc9th.domain.mission.enums.MissionState;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberMissionResDTO {
    @Builder
    public record AddMemberMissionDTO(
            Long memberMissionId,
            MissionState state,
            LocalDateTime createdAt
    ){}
}
