package com.naho.umc9th.domain.review.converter;

import com.naho.umc9th.domain.review.dto.ReviewResDTO;
import com.naho.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ReviewConverter {


    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                    .map(ReviewConverter::toReviewPreviewDTO)
                    .toList()
                )
                .listSize(result.getSize())
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
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
