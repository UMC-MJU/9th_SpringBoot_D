package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.umc9th.domain.review.exception.code.ReviewErrorCode.MEMBER_NOT_FOUND;
import static com.example.umc9th.domain.review.exception.code.ReviewErrorCode.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Review createReview(ReviewReqDTO.CreateDTO dto, Long storeId) {

        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new ReviewException(MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();

        return reviewRepository.saveAndFlush(review);
    }

}
