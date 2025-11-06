package com.example.umc9th.domain.review.repository.querydsl;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    // Predicate를 받아 필터링된 리뷰 검색
    List<Review> searchReviews(Predicate predicate);
}
