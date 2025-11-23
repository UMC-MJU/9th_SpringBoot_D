package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.dto.PagedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내 리뷰 조회 + 필터링
    @GetMapping("/reviews/my")
    public ApiResponse<PagedResponse<ReviewResDTO.MyReviewPreview>> getMyFilteredReviews(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            Pageable pageable) {

        Page<Review> reviewPage = reviewQueryService.getMyFilteredReviews(memberId, storeName, star, pageable);
        PagedResponse<ReviewResDTO.MyReviewPreview> response = ReviewConverter.toPagedMyReviewPreview(reviewPage);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, response);
    }

    // 가게에 리뷰 추가하기
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateDTO dto
    ) {
        Review review = reviewCommandService.createReview(dto, storeId);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                ReviewConverter.toCreateDTO(review)
        );
    }
}
