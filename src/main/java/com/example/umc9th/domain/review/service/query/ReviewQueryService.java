package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    Page<ReviewDto> searchReview(String query, String type, Pageable pageable);

    Page<ReviewDto> searchReviewByMemberId(Long memberId, String query, String type, Pageable pageable);

    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page);

    ReviewResDTO.ReviewListDTO findReviewByStoreId(Long storeId, Integer page);
}
