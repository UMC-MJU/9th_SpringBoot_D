package com.example.umc9th.repository.member.board;

import org.springframework.stereotype.Repository;

import com.example.umc9th.domain.member.board.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;        
import static com.example.umc9th.domain.member.board.QReview.review;
import static com.example.umc9th.domain.store.QStore.store;
import static com.example.umc9th.domain.member.board.QReviewAnswer.reviewAnswer;
import static com.example.umc9th.domain.common.QPhoto.photo;

@Repository
public class ReviewQueryDslImpl implements ReviewQueryDsl{


    private final JPAQueryFactory queryFactory;

    public ReviewQueryDslImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Review> findMyReviews(
        Long memberId,
        Long storeId,
        String storeName,
        Integer minRating,
        Integer maxRating,
        Pageable pageable
    ){
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));
        builder.and(storeIdCondition(storeId));
        builder.and(storeNameCondition(storeName));
        builder.and(ratingRangeCondition(minRating, maxRating));

        List<Review> reviews = queryFactory
            .selectFrom(review)
            .join(review.store, store).fetchJoin()
            .leftJoin(review.reviewAnswer, reviewAnswer).fetchJoin()
            .leftJoin(review.photos, photo).fetchJoin()
            .where(builder)
            .orderBy(review.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .distinct() //중복 제거
            .fetch();

        Long total = queryFactory
            .select(review.countDistinct())
            .from(review)
            .join(review.store, store)
            .leftJoin(review.reviewAnswer, reviewAnswer)
            .leftJoin(review.photos, photo)
            .where(builder)
            .fetchOne();

        return new PageImpl<>(reviews, pageable, total != null ? total : 0);
    }

    //가게 ID 조건
    private BooleanExpression storeIdCondition(Long storeId){
        return storeId != null
            ? store.id.eq(storeId)
            : null;
    }


    //가게 이름 조건
    private BooleanExpression storeNameCondition(String storeName){
        return StringUtils.hasText(storeName)
            ? store.name.contains(storeName)
            : null;
    }

    //별점 범위 조건
    private BooleanExpression ratingRangeCondition(Integer minRating, Integer maxRating){
        BooleanExpression condition = null;

        if(minRating != null && maxRating != null){
            condition = review.rating.between(minRating, maxRating);
        }else if(minRating != null){
            condition = review.rating.goe(minRating);
        }else if(maxRating != null){
            condition = review.rating.loe(maxRating);
        }
        return condition;
    }
}
