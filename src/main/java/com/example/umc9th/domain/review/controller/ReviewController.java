package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.config.PageRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class ReviewController implements ReviewControllerDocs{
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    public ReviewController(ReviewQueryService reviewQueryService, ReviewCommandService reviewCommandService) {
        this.reviewQueryService = reviewQueryService;
        this.reviewCommandService = reviewCommandService;
    }

    @Override
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

    @Override
    @GetMapping("/store/{storeId}/reviews/")
    // 구현체에 유효성 검사 시 문제 발생
    public ApiResponse<ReviewResDTO.ReviewListDTO>  getReviewsByStoreId(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReviewByStoreId(storeId, page));
    }

    // 내가 작성한 리뷰 목록 이미 있지만, 연습 용으로 새롭게 구현
    @Override
    @GetMapping("/user/{memberId}/reviews/")
    public ApiResponse<ReviewResDTO.ReviewListDTO> getReviewsByMemberId(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReviewByMemberId(memberId, page));
    }

    @GetMapping("/reviews/search")
    public ApiResponse<Page<ReviewDto>> searchReview(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type,
            PageRequest pageRequest
    ) throws Exception {
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        Pageable pageable = pageRequest.of();

        Page<ReviewDto> result = reviewQueryService.searchReview(query, type, pageable);
        return ApiResponse.onSuccess(code, result);
    }

    @GetMapping("/user/{memberId}/reviews/search") // 인증 인가 처리가 없어 일단 Path로 memberId 처리
    public ApiResponse<Page<ReviewDto>> searchReviewByMemberId(
            @PathVariable Long memberId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type,
            PageRequest pageRequest
    ) throws Exception {
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        Pageable pageable = pageRequest.of();

        Page<ReviewDto> result = reviewQueryService.searchReviewByMemberId(memberId, query, type, pageable);
        return ApiResponse.onSuccess(code, result);
    }

    @PostMapping("/user/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.AddDTO> addReview(
            @RequestBody @Valid ReviewReqDTO.AddDTO dto
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewCommandService.addReview(dto));
    }
}
