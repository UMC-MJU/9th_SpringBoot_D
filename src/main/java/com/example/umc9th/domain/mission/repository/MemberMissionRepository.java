package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 페이징과 함께 mission, mission.store를 함께 로드하도록 EntityGraph 사용
    @EntityGraph(attributePaths = {"mission", "mission.store"})
    Page<MemberMission> findByMember_IdAndStatusOrderByMission_DeadlineDesc(Long memberId, Status status, Pageable pageable);

    // 진행중(ONGOING) / 완료(COMPLETED) 조회
    default Page<MemberMission> findOngoingMissionsByMemberId(Long memberId, Pageable pageable) {
        return findByMember_IdAndStatusOrderByMission_DeadlineDesc(memberId, Status.ONGOING, pageable);
    }

    default Page<MemberMission> findCompletedMissionsByMemberId(Long memberId, Pageable pageable) {
        return findByMember_IdAndStatusOrderByMission_DeadlineDesc(memberId, Status.COMPLETED, pageable);
    }
}
