package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 하나의 미션은 특정 가게(Store)에 속함
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "conditional", length = 255)
    private String conditional;

    @Column(name = "point", nullable = false)
    private Integer point;

    // 한 미션은 여러 회원-미션(MemberMission)을 가질 수 있음
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

}
