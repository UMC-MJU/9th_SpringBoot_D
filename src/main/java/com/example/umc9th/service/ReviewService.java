package com.example.umc9th.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.example.umc9th.repository.member.MemberRepository;
import com.example.umc9th.repository.member.board.ReviewRepository;
import com.example.umc9th.repository.store.StoreRepository;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.example.umc9th.dto.ReviewResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    
    @Transactional
    public Review createReview(Long memberId, Long storeId, String content, Integer rating){

        //회원과 가게 존재 여부 확인
        Member member = memberRepository.findById(memberId)
            .orElseThrow(()->new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
            .orElseThrow(()->new BusinessException(ErrorCode.STORE_NOT_FOUND));

        //이미 리뷰가 존재하는지 확인 (중복 방지)
        if(reviewRepository.existsByMemberIdAndStoreId(memberId, storeId)){
            throw new BusinessException(ErrorCode.REVIEW_ALREADY_EXISTS);
        }

        //평점 유효성 검사
        if(rating < 1 || rating > 5){
            throw new BusinessException(ErrorCode.INVALID_RATING);
        }

        //리뷰 생성 및 저장
        Review review = Review.builder()
            .member(member)
            .store(store)
            .content(content)
            .rating(rating)
            .build();

        return reviewRepository.save(review);
        
    }

    //내가 작성한 리뷰 조회(가게별, 별점별 필터링)
    public Page<ReviewResponse> getMyReviews(
        Long memberId,
        Long storeId,
        String storeName,
        Integer minRating,
        Integer maxRating,
        Pageable pageable
    ){
        //동적 쿼리 실행
        Page<Review> reviewPage = reviewRepository.findMyReviews(
            memberId,
            storeId,
            storeName,
            minRating,
            maxRating,
            pageable
        );

        List<ReviewResponse> reviewResponses = reviewPage.getContent().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
        
        return new PageImpl<>(
            reviewResponses,
            pageable,
            reviewPage.getTotalElements()
        );
    }

    private ReviewResponse convertToResponse(Review review){

        ReviewResponse.StoreInfo storeInfo = ReviewResponse.StoreInfo.builder()
            .storeId(review.getStore().getId())
            .storeName(review.getStore().getName())
            .build();

        //리뷰 사진 정보 변환
        List<ReviewResponse.PhotoInfo> photoInfos = review.getPhotos().stream()
            .map(photo -> ReviewResponse.PhotoInfo.builder()
                .photoId(photo.getId())
                .imageUrl(photo.getImageUrl())
                .build())
            .collect(Collectors.toList());

        ReviewResponse.ReviewAnswerInfo reviewAnswerInfo = null;
        if(review.getReviewAnswer() != null){
            reviewAnswerInfo = ReviewResponse.ReviewAnswerInfo.builder()
                .reviewAnswerId(review.getReviewAnswer().getId())
                .content(review.getReviewAnswer().getContent())
                .createdAt(review.getReviewAnswer().getCreatedAt())
                .build();
        }

        return ReviewResponse.builder()
            .reviewId(review.getId())
            .content(review.getContent())
            .rating(review.getRating())
            .createdAt(review.getCreatedAt())
            .store(storeInfo)
            .photos(photoInfos)
            .reviewAnswer(reviewAnswerInfo)
            .build();
    }

}
