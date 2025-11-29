package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String memberNickname,
            Float star,
            String content,
            LocalDateTime createdAt
    ){}
}