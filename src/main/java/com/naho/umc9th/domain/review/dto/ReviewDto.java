package com.naho.umc9th.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewDto {

    private String storeName;
    private Double rating;
    private String content;


    public ReviewDto(String storeName, Double rating, String content){
        this.storeName = storeName;
        this.rating = rating;
        this.content = content;
    }
}
