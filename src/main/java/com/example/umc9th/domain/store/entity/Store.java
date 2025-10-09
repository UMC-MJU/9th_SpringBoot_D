package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.store.entity.mapping.MemberFoodStore;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address_detail", nullable = false, length = 255)
    private String addressDetail;

    // 어느 지역에 속한 가게인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "manager_num", nullable = false)
    private Integer managerNum;

    // 사용자 선호 음식별 가게 매핑
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFoodStore> memberFoodStores = new ArrayList<>();

}
