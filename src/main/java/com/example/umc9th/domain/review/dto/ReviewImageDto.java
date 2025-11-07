package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.entity.ReviewImage;
import com.example.umc9th.domain.store.dto.StoreDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AllArgsConstructor 실제 사용과 엔티티가 달라서 사용 x
public class ReviewImageDto {
    private Long reviewImageId;
    private String imageUrl;

    @QueryProjection
    public ReviewImageDto(Long reviewImageId, String imageUrl) {
        this.reviewImageId = reviewImageId;
        this.imageUrl = imageUrl;
    }

    public static ReviewImageDto fromEntity(ReviewImage reviewImage) {
        return ReviewImageDto.builder()
                .reviewImageId(reviewImage.getId())
                .imageUrl(reviewImage.getImageUrl())
                .build();
    }

    public ReviewImage toEntity(Review review) {
        return ReviewImage.builder()
                .id(this.reviewImageId)
                .imageUrl(this.imageUrl)
                .review(review)
                .build();
    }
}
