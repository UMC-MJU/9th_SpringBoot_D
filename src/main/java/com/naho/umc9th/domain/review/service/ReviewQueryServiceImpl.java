package com.naho.umc9th.domain.review.service;

import com.naho.umc9th.domain.common.apiPayload.code.GeneralErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;
import com.naho.umc9th.domain.review.converter.ReviewConverter;
import com.naho.umc9th.domain.review.dto.ReviewResDTO;
import com.naho.umc9th.domain.review.entity.QReview;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import com.naho.umc9th.domain.store.entity.Store;
import com.naho.umc9th.domain.store.exception.StoreErrorcode;
import com.naho.umc9th.domain.store.exception.StoreException;
import com.naho.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.storeRepository = storeRepository;
    }

    // 리뷰를 조건에 따라 동적으로 검색
    @Override
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
                try {
                    builder.and(review.rating.goe(Float.parseFloat(query)));
                } catch (NumberFormatException e) {
                    // "ABC" 같은 값이 오면 400 에러
                    throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
                }
            }

            if(type.equals("both")){
                try{
                    String[] parts = query.split("&");
                    String firstQuery = parts[0];
                    String secondQuery = parts[1];

                    // 동적 쿼리
                    builder.and(review.store.detailAddress.contains(firstQuery));
                    builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
                } catch (Exception e){
                    throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
                }
            }
        }

        // Repository 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        return reviewList;

    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName, Integer page
    ){
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorcode.NOT_FOUND));

        //page - 1을 해서 0부터 시작하도록 맞춤
        PageRequest pageRequest = PageRequest.of(page - 1, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }


}
