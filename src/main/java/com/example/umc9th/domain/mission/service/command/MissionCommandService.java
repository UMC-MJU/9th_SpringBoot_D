package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.mission.dto.mapping.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionResDTO;

public interface MissionCommandService {
    MemberMissionResDTO.AddMemberMissionDTO progressMission(MemberMissionReqDTO.AddMemberMissionDTO dto);
}
