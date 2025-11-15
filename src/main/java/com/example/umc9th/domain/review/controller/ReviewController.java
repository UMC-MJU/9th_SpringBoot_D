package com.example.umc9th.domain.review.controller;


import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my")
    public ApiResponse<PagedResponse<ReviewResDto.MyReviewPreview>> getMyFilteredReviews(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            Pageable pageable) {

        Page<Review> reviewPage = reviewQueryService.getMyFilteredReviews(memberId, storeName, star, pageable);

        PagedResponse<ReviewResDto.MyReviewPreview> response = ReviewConverter.toPagedMyReviewPreview(reviewPage);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }
}
