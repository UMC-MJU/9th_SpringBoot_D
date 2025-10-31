package com.naho.umc9th.domain.mission.entity;

import com.naho.umc9th.domain.common.entity.BaseEntity;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.mission.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
@EntityListeners(AuditingEntityListener.class)
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MissionStatus status;


    @Column(name = "completed_at")
    private LocalDateTime completedAt;


}
