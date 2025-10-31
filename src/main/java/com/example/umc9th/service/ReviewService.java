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

}
