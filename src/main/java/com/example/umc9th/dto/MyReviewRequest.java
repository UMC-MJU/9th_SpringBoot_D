package com.example.umc9th.dto;

public record MyReviewRequest(
    Long memberId,
    Long storeId,
    String storeName,
    Integer minRating,
    Integer maxRating,
    String sortBy,
    String sortDirection
) {}
