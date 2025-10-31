package com.naho.umc9th.domain.review.service;

import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import com.naho.umc9th.domain.review.dto.ReviewCreateRequestDto;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import com.naho.umc9th.domain.store.entity.Store;
import com.naho.umc9th.domain.store.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Review createReview(ReviewCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.memberId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다."));

        Store store = storeRepository.findById(requestDto.storeId())
                .orElseThrow(() -> new EntityNotFoundException("해당 가게를 찾을 수 없습니다."));

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
