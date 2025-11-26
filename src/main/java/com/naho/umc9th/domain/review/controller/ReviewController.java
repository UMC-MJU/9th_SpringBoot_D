package com.naho.umc9th.domain.review.controller;

import com.naho.umc9th.domain.common.apiPayload.ApiResponse;
import com.naho.umc9th.domain.review.dto.ReviewResDTO;
import com.naho.umc9th.domain.review.dto.ReviewSearchCond;
import com.naho.umc9th.domain.review.exception.ReviewSuccessCode;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import com.naho.umc9th.domain.review.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewQueryService reviewQueryService;

    // 내가 쓴 리뷰 목록 조회
    @GetMapping("/reviews/myReview")
    public ResponseEntity<Page<ReviewResDTO.ReviewDetailDto>> getMyReviews(
            //실제 환경에 맞게 로그인 유저 ID를 가져와야함

            @ModelAttribute ReviewSearchCond cond,

            @PageableDefault(size = 10)Pageable pageable
    ) {
        //임시 memberId 하드코딩
        Long memberId = 1L;

        Page<ReviewResDTO.ReviewDetailDto> myReviews = reviewRepository.findMyReviews(memberId, cond, pageable);

        return ResponseEntity.ok(myReviews);
    }


    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 나호 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패"),
    })
    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode._OK;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }

}
