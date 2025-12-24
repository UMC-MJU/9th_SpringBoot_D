package com.example.umc9th.domain.store.entity.mapping;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "member_food_store",
        uniqueConstraints = @UniqueConstraint(columnNames = {"store_id", "member_food_id"})
)
public class MemberFoodStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 가게와 연결되어 있는지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    //어떤 회원의 음식 취향과 연결되어 있는지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_food_id", nullable = false)
    private MemberFood memberFood;

}
