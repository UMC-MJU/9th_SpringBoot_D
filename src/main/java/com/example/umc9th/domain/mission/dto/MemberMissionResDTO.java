package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberMissionResDTO {

    @Builder
    @Getter
    public static class ChallengeDTO {
        private Long memberMissionId;
        private Long missionId;

        private Long storeId;
        private String storeName;

        private Status status;
        private LocalDateTime deadline;
        private Integer point;

        private LocalDateTime createdAt;
    }

}