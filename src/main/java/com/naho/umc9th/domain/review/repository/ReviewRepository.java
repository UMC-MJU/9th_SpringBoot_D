package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom, ReviewQueryDsl {
    //Jpa의 기본 메서드 + ReviewRepositoryCustom의 메서드 모두 사용 가능
    Page<Review> findAllByStore(Store store, Pageable pageable);

    Page<Review> findAllByMember(Member member, Pageable pageable);
}
