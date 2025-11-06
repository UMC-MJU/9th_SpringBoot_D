package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.querydsl.ReviewQueryDsl;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {

    // 특정 가게(Store)에 작성된 모든 리뷰 (삭제되지 않은 것만)
    List<Review> findByStoreAndDeletedAtIsNull(Store store);

    // 특정 회원(Member)이 작성한 리뷰 (마이페이지용)
    List<Review> findByMemberAndDeletedAtIsNull(Member member);

    // 닉네임으로 리뷰 조회 (가게 관리자 페이지에서 검색 가능)
    List<Review> findByMember_NicknameAndDeletedAtIsNull(String nickname);
}
