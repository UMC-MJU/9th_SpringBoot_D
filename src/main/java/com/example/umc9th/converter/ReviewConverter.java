package com.example.umc9th.converter;

import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.dto.MyReviewResponseDTO;
import com.example.umc9th.dto.ReviewResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static MyReviewResponseDTO toMyReviewResponseDTO(Review review) {
        return MyReviewResponseDTO.builder()
                .ownerNickname(review.getMember().getNickName())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .score(review.getRating().doubleValue())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponse toReviewResponseDTO(Review review) {
        ReviewResponse.StoreInfo storeInfo = new ReviewResponse.StoreInfo(
                review.getStore().getId(),
                review.getStore().getName());

        // 리뷰 사진 정보 변환
        List<ReviewResponse.PhotoInfo> photoInfos = review.getPhotos().stream()
                .map(photo -> new ReviewResponse.PhotoInfo(
                        photo.getId(),
                        photo.getImageUrl()))
                .collect(Collectors.toList());
        // 사장님 답글 변환
        ReviewResponse.ReplyInfo replyInfo = null;
        if (review.getReviewAnswer() != null) {
            replyInfo = new ReviewResponse.ReplyInfo(
                    review.getReviewAnswer().getId(),
                    review.getReviewAnswer().getContent(),
                    review.getReviewAnswer().getCreatedAt());
        }
        // 최종 dto 반환
        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getRating() != null ? review.getRating().floatValue() : null,
                replyInfo,
                review.getRating(),
                review.getCreatedAt(),
                storeInfo,
                photoInfos);
    }
}
