package com.example.umc9th.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewResponseDTO {
    private String ownerNickname;
    private String storeName;
    private String content;
    private Double score;
    private LocalDate createdAt;
}
