package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    List<Review> searchReview(Predicate predicate);
}
