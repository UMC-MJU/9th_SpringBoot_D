package com.naho.umc9th.domain.member.converter;

import com.naho.umc9th.domain.common.auth.enums.Role;
import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.enums.MemberStatus;
import com.naho.umc9th.domain.member.enums.Provider;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.entity.ReviewPhoto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member, String accessToken
    ){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDto.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .phoneNumber(dto.phoneNumber())
                .birthday(dto.birthday())
                .address(dto.address())
                .gender(dto.gender())

                .status(MemberStatus.ACTIVE)
                .point(0)
                .provider(Provider.GOOGLE)
                .providerId("temp_provider_id")
                .build();
    }

    //리뷰 단건 변환(Entity -> DTO)
    public static MemberResDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review){

        // 1. 사진 리스트 가져오기
        List<String> imageUrls = review.getReviewPhotoList().stream()
                .map(ReviewPhoto::getPhotoUrl)
                .collect(Collectors.toList());

        // 2. 사장님 답글 가져오기
        String replyContent = null;
        if(review.getReviewComment() != null){
            replyContent = review.getReviewComment().getContent();
        }

        // 3. DTO 생성
        return MemberResDTO.ReviewPreViewDTO.builder()
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .ownerReply(replyContent)
                .imageList(imageUrls)
                .build();
    }

    //리뷰 목록 변환 (Page<Review> -> DTO)
    public static MemberResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList) {

        List<MemberResDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(MemberConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());

        return MemberResDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();

    }
}
