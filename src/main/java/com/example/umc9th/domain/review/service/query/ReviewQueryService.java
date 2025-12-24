package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreViewListDTO findMyReviews(Long memberId, String storeName, Integer star, Integer page);

    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);
}
