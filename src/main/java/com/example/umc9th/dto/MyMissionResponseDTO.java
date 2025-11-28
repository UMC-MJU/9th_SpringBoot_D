package com.example.umc9th.dto;

import com.example.umc9th.domain.member.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyMissionResponseDTO {
    private Long missionId;
    private String storeName;
    private String missionDescription;
    private Integer rewardPoints;
    private LocalDate endDate;
    private MissionStatus status;
}
