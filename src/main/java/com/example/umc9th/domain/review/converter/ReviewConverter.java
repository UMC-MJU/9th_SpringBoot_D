package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.dto.PagedResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 개별 DTO 변환
    public static ReviewResDTO.MyReviewPreview toMyReviewPreview(Review review) {
        return ReviewResDTO.MyReviewPreview.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // List 변환
    public static List<ReviewResDTO.MyReviewPreview> toMyReviewPreviewList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toMyReviewPreview)
                .collect(Collectors.toList());
    }

    // Page 변환
    public static PagedResponse<ReviewResDTO.MyReviewPreview> toPagedMyReviewPreview(Page<Review> reviewPage) {
        return PagedResponse.<ReviewResDTO.MyReviewPreview>builder()
                .content(reviewPage.getContent().stream()
                        .map(ReviewConverter::toMyReviewPreview)
                        .collect(Collectors.toList()))
                .page(reviewPage.getNumber())
                .size(reviewPage.getSize())
                .totalElements(reviewPage.getTotalElements())
                .totalPages(reviewPage.getTotalPages())
                .build();
    }

    // Review 엔티티를 리뷰 생성 응답 DTO로 변환하는 메서드
    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
