package com.naho.umc9th.domain.mission.dto;

public record HomeMissionDto(
        Long MissionId,
        String MissionName,
        String description,
        String storeName
) {
}
