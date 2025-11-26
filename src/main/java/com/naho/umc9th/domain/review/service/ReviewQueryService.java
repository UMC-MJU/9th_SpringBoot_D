package com.naho.umc9th.domain.review.service;

import com.naho.umc9th.domain.review.dto.ReviewResDTO;
import com.naho.umc9th.domain.review.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewQueryService {

    // 리뷰를 조건에 따라 동적으로 검색
    List<Review> searchReview(String query, String type);


    ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName, Integer page
    );
}
