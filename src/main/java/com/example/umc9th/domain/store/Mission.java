package com.example.umc9th.domain.store;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.user.UserMission;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer rewardPoints;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    //이 미션을 수행한 사용자들의 기록
    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();
}
