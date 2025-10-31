package com.naho.umc9th.domain.review.dto;



public record ReviewCreateRequestDto (
        Long memberId,
        Long storeId,
        Double rating,
        String content
) {

}
