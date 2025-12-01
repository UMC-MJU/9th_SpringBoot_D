package com.example.umc9th.domain.review.dto;

import com.example.umc9th.domain.member.dto.MemberDto;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.dto.StoreDto;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record AddDTO(
      Long reviewId,
      LocalDateTime createdAt
    ){}

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Integer score,
            String body,
            LocalDate createdAt
    ){}

    @Builder
    public record ReviewListDTO(
            List<ReviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewDTO(
            Long reviewId,
            Long memberId,
            String comment,
            Integer star,
            LocalDateTime createdAt
    ){}
}
