package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.review.dto.ReviewResDTO;
import com.naho.umc9th.domain.review.dto.ReviewSearchCond;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

// Q-Type import
import static com.naho.umc9th.domain.review.entity.QReview.review;
import static com.naho.umc9th.domain.store.entity.QStore.store;

public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReviewResDTO.ReviewDetailDto> findMyReviews(Long memberId, ReviewSearchCond cond, Pageable pageable){

        // 1. 콘텐츠 조회(동적 쿼리)
        List<ReviewResDTO.ReviewDetailDto> content = queryFactory
                .select(Projections.constructor(ReviewResDTO.ReviewDetailDto.class,
                        store.name,
                        review.rating,
                        review.content
                ))
                .from(review)
                .join(review.store, store)
                .where(
                        review.member.id.eq(memberId),
                        storeNameEq(cond.getStoreName()),
                        ratingBetween(cond.getRatingFrom(), cond.getRatingTo())
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. 총 개수 조회
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(
                        review.member.id.eq(memberId),
                        storeNameEq(cond.getStoreName()),
                        ratingBetween(cond.getRatingFrom(), cond.getRatingTo())
                )
                .fetchOne();


        return new PageImpl<>(content, pageable, total);
    }

    // --- 동적 쿼리 메서드 ---

    private BooleanExpression storeNameEq(String storeName){
        return (storeName == null || storeName.isEmpty()) ? null : store.name.eq(storeName);
    }

    private BooleanExpression ratingBetween(Double ratingFrom, Double ratingTo){
        return (ratingFrom == null || ratingTo == null) ? null: review.rating.between(ratingFrom, ratingTo);
    }
}
