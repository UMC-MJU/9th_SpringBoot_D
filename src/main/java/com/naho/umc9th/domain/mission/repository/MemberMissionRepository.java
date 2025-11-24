package com.naho.umc9th.domain.mission.repository;

import com.naho.umc9th.domain.mission.dto.MemberMissionDto;
import com.naho.umc9th.domain.mission.entity.MemberMission;
import com.naho.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT new com.naho.umc9th.domain.mission.dto.MemberMissionDto(" +
            "m.name, " +
            "mm.status, " +
            "mm.createdAt, " +
            "mm.completedAt) " +
            "FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status IN :statuses")
    Page<MemberMissionDto> findMyMissionWithStatus(
            @Param("memberId") Long memberId,
            @Param("statuses") List<MissionStatus> statuses,
            Pageable pageable
    );

    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);


}
