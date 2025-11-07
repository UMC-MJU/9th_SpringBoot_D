package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.dto.ReviewImageDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.QReviewImage;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.global.entity.QAddress;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import static com.querydsl.core.group.GroupBy.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository // 워크북에는 Service로 되어있긴 한데 레포가 맞는 것 같아서 했습니다.
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public List<ReviewDto> searchReview(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QStore store = QStore.store;
        QAddress address = QAddress.address1;
        QReviewImage reviewImage = QReviewImage.reviewImage;

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store)
                .leftJoin(review.imageList, reviewImage)
                .leftJoin(store.address, address)
                .where(predicate)
                //.offset(pageable.getOffset())
                //.limit(pageable.getPageSize())
                .transform(
                        // review.id를 그룹 기준으로 하여 중복되는 review 엔티티 행을 하나의 그룹으로 묶음
                        groupBy(review.id).as(
                                Projections.constructor(
                                        ReviewDto.class,
                                        review.id,
                                        review.comment,
                                        review.star,
                                        list(
                                                Projections.constructor(
                                                        ReviewImageDto.class,
                                                        reviewImage.id,
                                                        reviewImage.imageUrl
                                                )
                                        )
                                )
                        )
                )
                .values().stream().collect(Collectors.toList());
    }
}
