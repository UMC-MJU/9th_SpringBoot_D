package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

public class ReviewConverter {

    // Review 엔티티를 리뷰 생성 응답 DTO로 변환하는 메서드
    public static ReviewResDTO.CreateDTO toCreateDTO(Review review) {
        return ReviewResDTO.CreateDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .memberNickname(review.getMember().getNickname())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
