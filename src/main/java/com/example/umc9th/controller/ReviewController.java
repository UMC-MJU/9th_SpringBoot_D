package com.example.umc9th.controller;

import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.dto.SearchReviewRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import com.example.umc9th.service.ReviewService;
import com.example.umc9th.dto.ReviewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import com.example.umc9th.service.ReviewQueryService;
import java.util.List;
import com.example.umc9th.dto.MyReviewRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.SuccessCode;
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    //검색 - 별점순 필터링 및 가게별 조건 추가
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Review>>> searchReview(
            @ModelAttribute SearchReviewRequest request
    ){      
        List<Review> reviews = reviewQueryService.searchReview(request);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.REVIEW200, reviews));
    }

    //내가 작성한 리뷰 조회
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getMyReviews(
        @ModelAttribute MyReviewRequest request,
        @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<ReviewResponse> reviews = reviewService.getMyReviews(request, pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.REVIEW200, reviews));
    }
}
