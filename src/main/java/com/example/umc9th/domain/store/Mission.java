package com.example.umc9th.domain.store;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.member.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.BatchSize;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Lob
    @Basic(fetch = FetchType.LAZY) // 성능 최적화: 필요할 때만 로드
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer rewardPoints;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    //이 미션을 수행한 사용자들의 기록
    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 100)
    private List<MemberMission> memberMissions = new ArrayList<>();

    //현재 도전 가능한 미션인지 확인
    public boolean isAvailable(){
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(this.startDate) && now.isBefore(this.endDate);
    }
}
