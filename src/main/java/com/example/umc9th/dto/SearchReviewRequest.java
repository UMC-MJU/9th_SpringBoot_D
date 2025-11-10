package com.example.umc9th.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchReviewRequest {
    private String query;
    private String type;
    private Long storeId;
    private String storeName;
    private Integer minRating;
    private Integer maxRating;
    private String sortBy;
    private String sortDirection;
}
