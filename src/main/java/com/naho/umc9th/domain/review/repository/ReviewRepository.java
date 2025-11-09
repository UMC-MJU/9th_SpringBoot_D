package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    //Jpa의 기본 메서드 + ReviewRepositoryCustom의 메서드 모두 사용 가능
}
