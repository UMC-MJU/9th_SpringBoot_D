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
import java.util.List;
import java.util.stream.Collectors;
import com.example.umc9th.dto.MyReviewResponseDTO;
import com.example.umc9th.converter.ReviewConverter;
import com.example.umc9th.dto.MyReviewRequest;
import com.example.umc9th.dto.review.CreateReviewRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 내가 작성한 리뷰 조회(가게별, 별점별 필터링)
    public Page<MyReviewResponseDTO> getMyReviews(MyReviewRequest request, Pageable pageable) {
        // 동적 쿼리 실행
        Page<Review> reviewPage = reviewRepository.findMyReviews(request, pageable);

        List<MyReviewResponseDTO> reviewResponses = reviewPage.getContent().stream()
                .map(ReviewConverter::toMyReviewResponseDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(
                reviewResponses,
                pageable,
                reviewPage.getTotalElements());
    }

    @Transactional
    public Review createReview(CreateReviewRequest request) {
        // 하드코딩: DB에 있는 첫 번째 회원 가져오기
        Member member = memberRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER001));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new BusinessException(ErrorCode.STORE001));

        // 이미 리뷰가 존재하는지 확인
        if (reviewRepository.existsByMemberIdAndStoreId(member.getId(), request.storeId())) {
            throw new BusinessException(ErrorCode.REVIEW001);
        }

        // 리뷰 생성 및 저장
        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(request.content())
                .rating(request.rating())
                .build();

        return reviewRepository.save(review);
    }

}
