package com.naho.umc9th.domain.review.service;

import com.naho.umc9th.domain.review.entity.QReview;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> searchReview(String query, String type){
        //Q클래스 정의
        QReview review = QReview.review;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        //동적 쿼리: 검색 조건
        if(query != null && !query.trim().isEmpty()){

            if("location".equals(type)){
                builder.and(review.store.detailAddress.contains(query));
            }

            if(type.equals("rating")){
                builder.and(review.rating.goe(Float.parseFloat(query)));
            }
            if(type.equals("both")){

                // & 기준 변환
                String firstQuery = query.split("&")[0];
                String secondQuery = query.split("&")[1];

                // 동적 쿼리
                builder.and(review.store.detailAddress.contains(firstQuery));
                builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
            }

        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;

    }


}
