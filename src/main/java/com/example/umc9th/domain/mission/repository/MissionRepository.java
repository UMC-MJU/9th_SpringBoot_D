package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.MissionState; // Enum 임포트 유지
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리
    @Query("select m " +
            "from MemberMission mm " +
            "join mm.mission m " +
            "join m.store s " +
            "where mm.member.id = :memberId " +
            "and mm.id < :cursorValue " +
            "and mm.state = :state " +
            "order by " +
            "mm.updatedAt desc, " +
            "mm.id desc " +
            "limit 20")
    List<Mission> findMissionsByMissionState(
            @Param("memberId") Long memberId,
            @Param("missionState") MissionState missionState,
            @Param("cursorValue") Long cursorValue
    );

    // 홈 화면 지역에 따라 지정한 진행 상태 미션 가져오기 (pending과 completed 상태 구분용)
    @Query("select m " +
            "from MemberMission mm " +
            "join mm.mission m " +
            "join m.store s " +
            "join s.address a " +
            "where mm.member.id = :memberId " +
            "and mm.state = :state " +
            "and a.sido = :sido " +
            "and mm.id < :cursorValue " +
            "order by " +
            "m.deadline desc, " +
            "mm.id desc " +
            "limit 20")
    List<Mission> findMissionsByMemberStateAndSido(
            @Param("memberId") Long memberId,
            @Param("state") MissionState state,
            @Param("sido") String sido,
            @Param("cursorValue") Long cursorValue);

    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}