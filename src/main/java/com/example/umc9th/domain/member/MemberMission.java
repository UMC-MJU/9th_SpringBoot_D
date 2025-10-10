package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.domain.member.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        //한 명의 사용자는 동일한 미션을 중복해서 수행할 수 없도록 유니크 제약조건을 설정
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_mission_unique",
                        columnNames = {"member_id", "mission_id"}
                )
        }
)
public class MemberMission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;

    //미션 완료 일시
    private LocalDateTime completedAt;

    //미션을 완료 상태로 변경하고, 완료 시간을 기록합니다.
    public void complete() {
        //이미 완료된 미션은 다시 완료할 수 없도록 방어 로직
        if(this.status == MissionStatus.COMPLETED){
            throw new IllegalStateException("이미 완료된 미션입니다.");
        }
        this.status = MissionStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }
}
