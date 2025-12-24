package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.enums.Address;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 — 현재 선택된 지역에서 도전 가능한 미션 목록 조회 (비로그인) / N + 1 방지
    @EntityGraph(attributePaths = {"store", "store.location"})
    @Query("""
        SELECT m
        FROM Mission m
        WHERE m.store.location.address = :address
          AND m.deadline > :now
        ORDER BY m.deadline ASC
    """)
    Page<Mission> findAvailableMissionsByLocation(
            @Param("address") Address address,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );

    // 홈 — 특정 멤버가 이미 완료한 미션을 제외한 현재 선택된 지역의 미션 목록 (로그인한 회원) / N + 1 방지
    @EntityGraph(attributePaths = {"store", "store.location"})
    @Query("""
        SELECT m
        FROM Mission m
        LEFT JOIN MemberMission mm
          ON mm.mission = m AND mm.member.id = :memberId
        WHERE m.store.location.address = :address
          AND m.deadline > :now
          AND (mm IS NULL OR mm.status <> com.example.umc9th.domain.mission.enums.Status.COMPLETED)
        ORDER BY m.deadline ASC
    """)
    Page<Mission> findAvailableMissionsByLocationExcludingMemberCompleted(
            @Param("memberId") Long memberId,
            @Param("address") Address address,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"store"})
    Page<Mission> findAllByStore(Store store, Pageable pageable);

}
