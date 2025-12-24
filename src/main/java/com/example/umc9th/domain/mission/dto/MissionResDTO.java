package com.example.umc9th.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionPreviewDTO {
        private Long missionId;
        private String storeName;
        private String conditional;
        private Integer point;
        private LocalDateTime deadline;
    }

    @Getter
    @Builder
    public static class MissionPreviewListDTO {
        private List<MissionPreviewDTO> missionList;
        private int listSize;
        private int totalPage;
        private long totalElements;
        private boolean isFirst;
        private boolean isLast;
    }
}