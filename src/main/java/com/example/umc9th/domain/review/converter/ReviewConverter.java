package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewConverter {
    public static ReviewResDTO.AddDTO toAddDTO(Review review) {
        return ReviewResDTO.AddDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(
            ReviewReqDTO.AddDTO dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .comment(dto.comment())
                .star(dto.star())
                .member(member)
                .store(store)
                .build();
    }
}