package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.dto.PagedResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 개별 DTO 변환
    public static ReviewResDto.MyReviewPreview toMyReviewPreview(Review review) {
        return ReviewResDto.MyReviewPreview.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // List 변환
    public static List<ReviewResDto.MyReviewPreview> toMyReviewPreviewList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toMyReviewPreview)
                .collect(Collectors.toList());
    }

    // Page 변환
    public static PagedResponse<ReviewResDto.MyReviewPreview> toPagedMyReviewPreview(Page<Review> reviewPage) {
        return PagedResponse.<ReviewResDto.MyReviewPreview>builder()
                .content(reviewPage.getContent().stream()
                        .map(ReviewConverter::toMyReviewPreview)
                        .collect(Collectors.toList()))
                .page(reviewPage.getNumber())
                .size(reviewPage.getSize())
                .totalElements(reviewPage.getTotalElements())
                .totalPages(reviewPage.getTotalPages())
                .build();
    }
}
