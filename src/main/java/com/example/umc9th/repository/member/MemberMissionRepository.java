package com.example.umc9th.repository.member;

import com.example.umc9th.domain.member.MemberMission;
import com.example.umc9th.domain.member.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    
    // 내가 진행중, 진행 완료한 미션 조회 (페이징 포함)
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN FETCH mm.mission m " +
           "JOIN FETCH m.store s " +
           "WHERE mm.member.id = :memberId " +
           "AND mm.status IN :statuses " +
           "ORDER BY mm.createdAt DESC")
    Page<MemberMission> findMyMissionsByStatuses(
            @Param("memberId") Long memberId,
            @Param("statuses") List<MissionStatus> statuses,
            Pageable pageable
    );
}