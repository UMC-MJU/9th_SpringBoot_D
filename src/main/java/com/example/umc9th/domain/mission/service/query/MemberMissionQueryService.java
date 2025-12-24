package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

public interface MemberMissionQueryService {

    // 진행중인 미션 조회
    Page<MemberMission> findOngoingMissions(Long memberId, Integer page);
}
