package com.naho.umc9th.domain.review.controller;

import com.naho.umc9th.domain.review.dto.ReviewDto;
import com.naho.umc9th.domain.review.dto.ReviewSearchCond;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/api/my-reviews")
    public ResponseEntity<Page<ReviewDto>> getMyReviews(
            //실제 환경에 맞게 로그인 유저 ID를 가져와야함

            @ModelAttribute ReviewSearchCond cond,

            @PageableDefault(size = 10)Pageable pageable
    ) {
        //임시 memberId 하드코딩
        Long memberId = 1L;

        Page<ReviewDto> myReviews = reviewRepository.findMyReviews(memberId, cond, pageable);

        return ResponseEntity.ok(myReviews);
    }
}
