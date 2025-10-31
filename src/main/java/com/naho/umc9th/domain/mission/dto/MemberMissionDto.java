package com.naho.umc9th.domain.mission.dto;

import com.naho.umc9th.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;

public record MemberMissionDto(
        String missionName,
        MissionStatus status,
        LocalDateTime createdAt,
        LocalDateTime completedAt
){

}
