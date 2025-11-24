package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record AddDTO(
      Long reviewId,
      LocalDateTime createdAt
    ){}
}
