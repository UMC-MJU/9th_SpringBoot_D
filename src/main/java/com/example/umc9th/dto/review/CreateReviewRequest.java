package com.example.umc9th.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public record CreateReviewRequest(
    @NotNull(message = "가게 ID를 입력해주세요.")
    Long storeId,

    @NotBlank(message = "리뷰 내용을 입력해주세요.")
    String content,

    @NotNull(message = "평점을 입력해주세요.")
    @Min(value = 1, message = "평점은 1 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 5 이하이어야 합니다.")
    Integer rating
) {
}
