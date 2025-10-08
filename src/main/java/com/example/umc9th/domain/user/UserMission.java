package com.example.umc9th.domain.user;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.domain.user.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        //한 명의 사용자는 동일한 미션을 중복해서 수행할 수 없도록 유니크 제약조건을 설정
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_mission_unique",
                        columnNames = {"user_id", "mission_id"}
                )
        }
)
public class UserMission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;

    //미션 완료 일시
    private LocalDateTime completedAt;
}
