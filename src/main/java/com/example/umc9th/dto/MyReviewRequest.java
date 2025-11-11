package com.example.umc9th.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewRequest {
    private Long memberId;     
    private Long storeId;
    private String storeName;
    private Integer minRating;
    private Integer maxRating;
    private String sortBy;
    private String sortDirection;
}
