package com.example.umc9th.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreMissionResponseDTO {
    private Long missionId;
    private String description;
    private Integer rewardPoints;
    private LocalDate endDate;
}
