package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import com.querydsl.core.annotations.QueryProjection;

import java.util.List;
import java.util.stream.Collectors;

public record ReviewDto (
        Long reviewId,
        String comment,
        Integer star,
        StoreDto storeDto,
        MemberDto memberDto,
        List<ReviewImageDto> reviewImageList
){


    @QueryProjection
    public ReviewDto(Long reviewId, String comment, Integer star, List<ReviewImageDto> reviewImageList) {
        this(reviewId, comment, star, null, null, reviewImageList);
    }

    public static ReviewDto fromEntity(Review review) {
        List<ReviewImageDto> images = review.getImageList() != null ?
                review.getImageList().stream()
                        .map(ReviewImageDto::fromEntity)
                        .collect(Collectors.toList())
                : null;
        return new ReviewDto(
                review.getId(),
                review.getComment(),
                review.getStar(),
                StoreDto.fromEntity(review.getStore()),
                MemberDto.fromEntity(review.getMember()),
                images
        );
    }

    public Review toEntity() {
        return Review.builder()
                .id(this.reviewId)
                .comment(this.comment)
                .star(this.star)
                .store(this.storeDto.toEntity())
                .member(this.memberDto.toEntity())
                .build();
    }
}
