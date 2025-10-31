package com.naho.umc9th.domain.mission.repository;

import com.naho.umc9th.domain.common.enums.District;
import com.naho.umc9th.domain.mission.dto.HomeMissionDto;
import com.naho.umc9th.domain.mission.entity.MemberMission;
import com.naho.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HomeMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("SELECT new com.naho.umc9th.domain.mission.dto.HomeMissionDto(" +
            " m.id, " +
            " m.name, " +
            " m.content, " +
            " s.name)" +
            "FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "JOIN m.store s " +
            "JOIN s.region r " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status = :status " +
            "AND r.city = :city " +
            "AND r.district = :district")
    Page<HomeMissionDto> findInProgressMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("status")MissionStatus status,
            @Param("city") String city,
            @Param("district") District district,
            Pageable pageable
    );
}
