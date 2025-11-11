package com.example.umc9th.controller;

import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.dto.SearchReviewRequest;
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
import com.example.umc9th.service.ReviewQueryService;
import java.util.List;
import com.example.umc9th.dto.MyReviewRequest;
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    //검색 - 별점순 필터링 및 가게별 조건 추가
    @GetMapping("/search")
    public List<Review> searchReview(
            @RequestParam String query,
            @RequestParam String type,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer minRating,
            @RequestParam(required = false) Integer maxRating,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection
    ){
        SearchReviewRequest request = SearchReviewRequest.builder()
            .query(query)
            .type(type)
            .storeId(storeId)
            .storeName(storeName)
            .minRating(minRating)
            .maxRating(maxRating)
            .sortBy(sortBy)
            .sortDirection(sortDirection)
            .build();
            
        return reviewQueryService.searchReview(request);
    }

    //내가 작성한 리뷰 조회
    @GetMapping("/my")
    public ResponseEntity<Page<ReviewResponse>> getMyReviews(
        @RequestParam Long memberId,
        @RequestParam(required = false) Long storeId,
        @RequestParam(required = false) String storeName,
        @RequestParam(required = false) Integer minRating,
        @RequestParam(required = false) Integer maxRating,
        @RequestParam(required = false) String sortBy,
        @RequestParam(required = false) String sortDirection,
        @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        MyReviewRequest request = MyReviewRequest.builder()
            .memberId(memberId)
            .storeId(storeId)
            .storeName(storeName)
            .minRating(minRating)
            .maxRating(maxRating)
            .sortBy(sortBy)
            .sortDirection(sortDirection)
            .build();

        return ResponseEntity.ok(reviewService.getMyReviews(request, pageable));
    }
}
