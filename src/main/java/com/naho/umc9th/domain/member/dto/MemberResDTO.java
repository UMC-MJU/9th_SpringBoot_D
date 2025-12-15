package com.naho.umc9th.domain.member.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}

    //리뷰 목록을 감싸는 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    //리뷰 단건 정보 DTO
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String storeName;
        Double rating;
        String content;
        LocalDate createdAt;
        String ownerReply;
        List<String> imageList;
    }

    //로그인
    @Builder
    public record LoginDTO(
            Long memberId,
            String accessToken
    ){}
}
