package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDto {

    @Builder
    @Getter
    public static class MyReviewPreview{
        private Long reviewId;
        private String storeName;
        private String content;
        private Float star;
        private LocalDateTime createdAt;
    }
}
