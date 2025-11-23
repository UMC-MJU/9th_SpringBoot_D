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
import com.example.umc9th.global.apiPayload.code.ErrorCode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.example.umc9th.dto.ReviewResponse;
import java.util.List;
import java.util.stream.Collectors;
import com.example.umc9th.dto.MyReviewRequest;

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
            .orElseThrow(()->new BusinessException(ErrorCode.MEMBER001));

        Store store = storeRepository.findById(storeId)
            .orElseThrow(()->new BusinessException(ErrorCode.STORE001));

        //이미 리뷰가 존재하는지 확인 (중복 방지)
        if(reviewRepository.existsByMemberIdAndStoreId(memberId, storeId)){
            throw new BusinessException(ErrorCode.REVIEW001);
        }

        //평점 유효성 검사
        if(rating < 1 || rating > 5){
            throw new BusinessException(ErrorCode.REVIEW4001);
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
    public Page<ReviewResponse> getMyReviews(MyReviewRequest request, Pageable pageable){
        //동적 쿼리 실행
        Page<Review> reviewPage = reviewRepository.findMyReviews(request,pageable);

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

        ReviewResponse.StoreInfo storeInfo = new ReviewResponse.StoreInfo(
            review.getStore().getId(),
            review.getStore().getName()
        );

        //리뷰 사진 정보 변환
        List<ReviewResponse.PhotoInfo> photoInfos = review.getPhotos().stream()
            .map(photo -> new ReviewResponse.PhotoInfo(
                photo.getId(),
                photo.getImageUrl()
            ))
            .collect(Collectors.toList());
        //사장님 답글 변환
        ReviewResponse.ReplyInfo replyInfo = null;
        if(review.getReviewAnswer() != null){
            replyInfo = new ReviewResponse.ReplyInfo(
                review.getReviewAnswer().getId(),
                review.getReviewAnswer().getContent(),
                review.getReviewAnswer().getCreatedAt()
            );
        }
        //최종 dto 반환
        return new ReviewResponse(
            review.getId(),
            review.getContent(),
            review.getRating() != null ? review.getRating().floatValue() : null,
            replyInfo,
            review.getRating(),
            review.getCreatedAt(),
            storeInfo,
            photoInfos
        );
    }

}
