package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class ReviewReqDTO {

    public record CreateDTO(

            @NotNull(message = "회원 ID는 필수입니다.")
            Long memberId,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.5", message = "별점은 최소 0.5 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 최대 5.0 이하여야 합니다.")
            Float star,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content

    ) {}
}
