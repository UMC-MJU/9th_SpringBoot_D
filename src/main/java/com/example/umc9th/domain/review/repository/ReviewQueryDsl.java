package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewQueryDsl {

    List<ReviewDto> searchReview(Predicate predicate, Pageable pageable);
}
