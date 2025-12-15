package com.naho.umc9th.domain.member.service.query;

import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDTO.ReviewPreViewListDTO getReviewList(Long memberId, Integer page);

    MemberResDTO.LoginDTO login(@Valid MemberReqDto.LoginDTO dto);
}
