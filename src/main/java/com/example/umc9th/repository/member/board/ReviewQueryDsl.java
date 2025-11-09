package com.example.umc9th.repository.member.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.umc9th.domain.member.board.Review;

public interface ReviewQueryDsl {

    //내가 작성한 리뷰 조회(가게별, 별점별 필터링)
    Page<Review> findMyReviews(
        Long memberId,
        Long storeId,
        String storeName,
        Integer minRating,
        Integer maxRating,
        Pageable pageable
    );
}
