package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.review.dto.ReviewResDTO;
import com.naho.umc9th.domain.review.dto.ReviewSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<ReviewResDTO.ReviewDetailDto> findMyReviews(Long memberId, ReviewSearchCond cond, Pageable pageable);
}
