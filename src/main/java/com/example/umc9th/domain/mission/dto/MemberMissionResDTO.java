package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    public static class ChallengeListDTO {
        private List<ChallengeDTO> missionList;
        private int listSize;
        private int totalPage;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }
}