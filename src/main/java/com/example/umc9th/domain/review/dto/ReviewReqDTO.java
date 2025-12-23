package com.example.umc9th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDTO {

    public record AddDTO(
            @NotNull(message = "유저 Id는 필수 입력 값입니다.")
            Long memberId,
            @NotNull(message = "가게 Id는 필수 입력 값입니다.")
            Long storeId,
            @NotBlank(message = "코멘트는 빈 칸이 될 수 없습니다.")
            @NotNull(message = "코멘트는 필수 입력 값입니다.")
            String comment,
            @NotNull(message = "평점은 필수 입력 값입니다.")
            Integer star
            //List<String> imageUrls //일단 나중에 추가
    ){}
}
