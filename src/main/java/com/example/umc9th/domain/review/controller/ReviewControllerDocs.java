package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.global.annotation.ValidPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {

    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 비비 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam @ValidPage Integer page
    );

    @Operation(summary = "내 리뷰 조회", description = "특정 회원의 리뷰를 페이지네이션으로 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            @RequestParam @ValidPage Integer page
            );
}
