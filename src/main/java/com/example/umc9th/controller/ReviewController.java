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
import com.example.umc9th.dto.review.CreateReviewRequest;
import jakarta.validation.Valid;
import com.example.umc9th.dto.MyReviewResponseDTO;
import com.example.umc9th.converter.ReviewConverter;
import com.example.umc9th.global.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {
        private final ReviewService reviewService;
        private final ReviewQueryService reviewQueryService;

        // 검색 - 별점순 필터링 및 가게별 조건 추가
        @GetMapping("/search")
        public ResponseEntity<ApiResponse<List<Review>>> searchReview(
                        @ModelAttribute SearchReviewRequest request) {
                List<Review> reviews = reviewQueryService.searchReview(request);
                return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.REVIEW200, reviews));
        }

        // 내가 작성한 리뷰 조회
        @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
        })
        @Parameters({
                        @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
        })
        @GetMapping("/my")
        public ResponseEntity<ApiResponse<Page<MyReviewResponseDTO>>> getMyReviews(
                        @ModelAttribute MyReviewRequest request,
                        @CheckPage @RequestParam(name = "page") Integer page,
                        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
                // 프론트엔드에서 1부터 시작하는 페이지 번호를 받으므로 0부터 시작하는 Pageable로 변환
                pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
                Page<MyReviewResponseDTO> reviews = reviewService.getMyReviews(request, pageable);
                return ResponseEntity.ok(ApiResponse.onSuccess(SuccessCode.REVIEW200, reviews));
        }

        @PostMapping("/write")
        public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
                        @Valid @RequestBody CreateReviewRequest request) {
                Review review = reviewService.createReview(request);
                ReviewResponse response = ReviewConverter.toReviewResponseDTO(review);
                return ResponseEntity.status(SuccessCode.REVIEW201.getStatus())
                                .body(ApiResponse.onSuccess(SuccessCode.REVIEW201, response));
        }
}
