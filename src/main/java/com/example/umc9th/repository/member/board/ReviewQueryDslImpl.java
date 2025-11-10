package com.example.umc9th.repository.member.board;

import org.springframework.stereotype.Repository;

import com.example.umc9th.domain.member.board.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
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
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.example.umc9th.dto.SearchReviewRequest;
@Repository
public class ReviewQueryDslImpl implements ReviewQueryDsl{


    private final JPAQueryFactory queryFactory;

    public ReviewQueryDslImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Review> searchReview(SearchReviewRequest request){
        BooleanBuilder builder = new BooleanBuilder();
        String query = request.getQuery();
        String type = request.getType();

            if("location".equals(type)){
                builder.and(store.address.addressName.contains(query));
            }else if("star".equals(type) || "rating".equals(type)){
                try{
                    Float rating = Float.parseFloat(query);
                    builder.and(review.rating.goe(rating.intValue()));
                }catch(NumberFormatException e){
                }
            }else if("both".equals(type)){
                String[] queries = query.split("&");
                if(queries.length >=2){
                    String firstQuery = queries[0];
                    String secondQuery = queries[1];

                    builder.and(store.address.addressName.contains(firstQuery));
                    try{
                        Float rating = Float.parseFloat(secondQuery);
                        builder.and(review.rating.goe(rating.intValue()));
                    }catch(NumberFormatException e){
                    }
                }
            }else if("storeName".equals(type)){
                builder.and(store.name.contains(query));
            }

            builder.and(storeIdCondition(request.getStoreId()));
            builder.and(storeNameCondition(request.getStoreName()));
            builder.and(ratingRangeCondition(request.getMinRating(), request.getMaxRating()));

            OrderSpecifier<?>[] orderSpecifiers = createOrderSpecifiers(
                request.getSortBy(), 
                request.getSortDirection()
            );

            List<Review> reviews = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .leftJoin(review.reviewAnswer, reviewAnswer).fetchJoin()
                .leftJoin(review.photos, photo).fetchJoin()
                .where(builder)
                .orderBy(orderSpecifiers)
                .fetch();
            return reviews;

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


    //동적 정렬 생성 메서드
    private OrderSpecifier<?>[] createOrderSpecifiers(String sortBy, String sortDirection){
        Order order = "asc".equalsIgnoreCase(sortDirection)
            ? Order.ASC:Order.DESC;

        if("rating".equalsIgnoreCase(sortBy)){
            return new OrderSpecifier[]{
                new OrderSpecifier<>(
                    order, review.rating
                ),
                new OrderSpecifier<>(Order.DESC, review.createdAt)
            };
        }else if ("storeRating".equalsIgnoreCase(sortBy)){
            return new OrderSpecifier[]{
                new OrderSpecifier<>(
                    order, store.averageRating
                ),
                new OrderSpecifier<>(Order.DESC, review.createdAt)
            };
        }else{
            return new OrderSpecifier[]{
                new OrderSpecifier<>(
                    Order.DESC, review.createdAt
                )
            };
        }
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
