package com.naho.umc9th.domain.store.entity;

import com.naho.umc9th.domain.common.entity.Region;
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
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    //향후 카테고리 확장 시 한 가게가 여러 카테고리를 가질 수 있음 -> 다대다
    @OneToMany(mappedBy = "store") //storeCategory의 store 필드와 연결,
    private List<StoreCategory> storeCategoryList = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "score")
    private Float score;

    @Column(name = "visitors")
    private Long visitors;

}
