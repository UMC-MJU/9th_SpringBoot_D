package com.naho.umc9th.domain.member.repository;

import com.naho.umc9th.domain.member.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory,Long> {
}
