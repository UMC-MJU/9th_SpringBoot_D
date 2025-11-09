package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.review.dto.ReviewDto;
import com.naho.umc9th.domain.review.dto.ReviewSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    Page<ReviewDto> findMyReviews(Long memberId, ReviewSearchCond cond, Pageable pageable);
}
