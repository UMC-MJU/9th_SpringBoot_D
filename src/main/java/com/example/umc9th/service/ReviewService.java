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
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));

        Store store = storeRepository.findById(storeId)
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 가게입니다."));

        //이미 리뷰가 존재하는지 확인 (중복 방지)
        if(reviewRepository.existsByMemberIdAndStoreId(memberId, storeId)){
            throw new IllegalArgumentException("이미 리뷰를 작성한 가게입니다.");
        }

        //평점 유효성 검사
        if(rating < 1 || rating > 5){
            throw new IllegalArgumentException("평점은 1~5 사이의 정수여야 합니다.");
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
