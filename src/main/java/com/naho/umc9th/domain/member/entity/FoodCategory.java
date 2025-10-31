package com.naho.umc9th.domain.member.entity;

import com.naho.umc9th.domain.member.enums.FoodCategoryName;
import com.naho.umc9th.domain.store.entity.StoreCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodCategoryName foodCategoryName;

    @OneToMany(mappedBy = "foodCategory", fetch = FetchType.LAZY)
    private List<StoreCategory> storeCategoryList = new ArrayList<>();

}
