package com.naho.umc9th.domain.review.repository;

import com.naho.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
