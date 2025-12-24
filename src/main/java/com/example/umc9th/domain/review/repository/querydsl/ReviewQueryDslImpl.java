package com.example.umc9th.domain.review.repository.querydsl;

import com.example.umc9th.domain.review.entity.QReply;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewPhoto;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public Page<Review> searchReviews(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        // 필터링된 리뷰 조회
        List<Review> results = queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 계산 (count 쿼리)
        Long total = Optional.ofNullable(
                queryFactory.select(review.count())
                        .from(review)
                        .where(predicate)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(results, pageable, total);
    }
}