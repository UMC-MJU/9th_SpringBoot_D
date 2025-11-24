package com.naho.umc9th.domain.mission.service;

import com.naho.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;

public interface MemberMissionCommandService {
    MemberMissionResDTO.CreateResultDTO createMemberMission(MemberMissionReqDTO.CreateDTO request);
}
