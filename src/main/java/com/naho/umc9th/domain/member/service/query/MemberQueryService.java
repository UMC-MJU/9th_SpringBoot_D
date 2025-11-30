package com.naho.umc9th.domain.member.service.query;

import com.naho.umc9th.domain.member.dto.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.ReviewPreViewListDTO getReviewList(Long memberId, Integer page);
}
