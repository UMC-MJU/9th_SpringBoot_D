package com.naho.umc9th.domain.review.service;

import com.naho.umc9th.domain.common.apiPayload.code.GeneralErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import com.naho.umc9th.domain.review.dto.ReviewReqDTO;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import com.naho.umc9th.domain.store.entity.Store;
import com.naho.umc9th.domain.store.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         MemberRepository memberRepository,
                         StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Review createReview(ReviewReqDTO.ReviewCreateRequestDto requestDto) {

        Member member = memberRepository.findById(requestDto.memberId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(requestDto.storeId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .rating(requestDto.rating())
                .content(requestDto.content())
                .build();

        Review savedReview = reviewRepository.save(review);

        return savedReview;



    }
}
