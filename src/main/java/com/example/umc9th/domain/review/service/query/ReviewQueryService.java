package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Page<Review> getMyFilteredReviews(Long memberId, String storeName, Integer star, Pageable pageable) {
        // Q클래스 정의
        QReview review = QReview.review;
        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // 동적 쿼리: 검색 조건
        if (memberId != null) {
            builder.and(review.member.id.eq(memberId));
        }

        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        if (star != null) {
            if (star == 5) {
                builder.and(review.star.eq(5.0f));
            } else {
                float min = star.floatValue();
                float max = star + 0.9f;
                builder.and(review.star.between(min, max));
            }
        }

        builder.and(review.deletedAt.isNull());
        Predicate predicate = builder;

        return reviewRepository.searchReviews(predicate, pageable);
    }
}
