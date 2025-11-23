package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class MyReviewPreview {
        private Long reviewId;
        private String storeName;
        private String content;
        private Float star;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class CreateDTO {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private LocalDateTime createdAt;
    }
}