package com.example.umc9th.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import com.example.umc9th.service.ReviewService;
import com.example.umc9th.dto.ReviewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //내가 작성한 리뷰 조회
    @GetMapping("/my")
    public ResponseEntity<Page<ReviewResponse>> getMyReviews(
        @RequestParam Long memberId,
        @RequestParam(required = false) Long storeId,
        @RequestParam(required = false) String storeName,
        @RequestParam(required = false) Integer minRating,
        @RequestParam(required = false) Integer maxRating,
        @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return ResponseEntity.ok(reviewService.getMyReviews(
            memberId,
            storeId,
            storeName,
            minRating,
            maxRating,
            pageable
        ));
    }
}
