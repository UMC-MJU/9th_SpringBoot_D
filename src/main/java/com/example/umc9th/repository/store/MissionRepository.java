package com.example.umc9th.repository.store;

import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.domain.member.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    
    /**
     * 현재 선택된 지역에서 도전 가능한 미션 목록을 조회
     */
    @Query("SELECT m FROM Mission m " +
           "JOIN m.store s " +
           "JOIN s.address a " +
           "WHERE a.addressName = :regionName " +
           "AND :now >= m.startDate " +
           "AND :now <= m.endDate " +
           "AND NOT EXISTS (" +
           "    SELECT mm FROM MemberMission mm " +
           "    WHERE mm.member.id = :memberId " +
           "    AND mm.mission.id = m.id " +
           "    AND (mm.status = :challengingStatus OR mm.status = :completedStatus)" +
           ") " +
           "ORDER BY m.createdAt DESC")
    Page<Mission> findAvailableMissionsByRegionAndMember(
            @Param("regionName") String regionName,
            @Param("memberId") Long memberId,
            @Param("now") LocalDateTime now,
            @Param("challengingStatus") MissionStatus challengingStatus,
            @Param("completedStatus") MissionStatus completedStatus,
            Pageable pageable
    );
}