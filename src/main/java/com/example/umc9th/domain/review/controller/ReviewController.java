package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController implements ReviewControllerDocs{

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내 리뷰 조회 + 필터링 (페이징)
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            @RequestParam @ValidPage Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, reviewQueryService.findMyReviews(memberId, storeName, star, page));
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

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam  @ValidPage Integer page
    ){
        ReviewSuccessCode code = ReviewSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }
}
