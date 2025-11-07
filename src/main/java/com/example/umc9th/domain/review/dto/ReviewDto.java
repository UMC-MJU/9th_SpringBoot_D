package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDto {
    private Long reviewId;
    private String comment;
    private Integer star;

    private StoreDto storeDto;
    private List<ReviewImageDto> reviewImageList;

    @QueryProjection
    public ReviewDto(Long reviewId, String comment, Integer star, List<ReviewImageDto> reviewImageList) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.star = star;
        this.reviewImageList = reviewImageList;
    }

    public static ReviewDto fromEntity(Review review) {
        List<ReviewImageDto> images = review.getImageList() != null ?
                review.getImageList().stream()
                        .map(ReviewImageDto::fromEntity)
                        .collect(Collectors.toList())
                : null;

        return ReviewDto.builder()
                .reviewId(review.getId())
                .comment(review.getComment())
                .star(review.getStar())
                .storeDto(StoreDto.fromEntity(review.getStore()))
                .reviewImageList(images)
                .build();
    }

    public Review toEntity() {
        return Review.builder()
                .id(this.reviewId)
                .comment(this.comment)
                .star(this.star)
                .store(this.storeDto.toEntity())
                .build();
    }
}
