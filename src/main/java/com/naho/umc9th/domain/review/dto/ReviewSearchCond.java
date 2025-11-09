package com.naho.umc9th.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchCond {

    //가게 이름 필터
    private String storeName;

    //별점 시작 (예: 4.0)
    private Double ratingFrom;

    //별점 끝 (예: 4.9)
    private Double ratingTo;
}
