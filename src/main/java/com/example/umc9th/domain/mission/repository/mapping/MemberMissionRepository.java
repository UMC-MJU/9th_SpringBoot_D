package com.example.umc9th.domain.mission.repository.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByMemberAndState(Member member, MissionState missionState, PageRequest pageRequest);
}
