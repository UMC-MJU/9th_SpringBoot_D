package com.naho.umc9th.domain.review.dto;

public class ReviewReqDTO {

    // 리뷰 생성 요청
    public record ReviewCreateRequestDto (
            Long memberId,
            Long storeId,
            Double rating,
            String content
    ) {}

}
