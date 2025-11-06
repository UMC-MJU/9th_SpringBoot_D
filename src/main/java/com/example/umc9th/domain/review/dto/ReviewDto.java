package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String comment;
    private Integer star;

    private StoreDto storeDto;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .comment(review.getComment())
                .star(review.getStar())
                .storeDto(StoreDto.fromEntity(review.getStore()))
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
