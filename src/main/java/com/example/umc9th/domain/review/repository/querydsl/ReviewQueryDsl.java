package com.example.umc9th.domain.review.repository.querydsl;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewQueryDsl {

    // Predicate + Pageable 기반 동적 쿼리
    Page<Review> searchReviews(Predicate predicate, Pageable pageable);
}
