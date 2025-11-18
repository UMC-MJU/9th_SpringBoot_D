package com.example.umc9th.dto;

public record SearchReviewRequest(
    String query,
    String type,
    Long storeId,
    String storeName,
    Integer minRating,
    Integer maxRating,
    String sortBy,
    String sortDirection
) {}
