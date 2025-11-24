package com.naho.umc9th.domain.mission.converter;

import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.naho.umc9th.domain.mission.entity.MemberMission;
import com.naho.umc9th.domain.mission.entity.Mission;
import com.naho.umc9th.domain.mission.enums.MissionStatus;

public class MemberMissionConverter {

    public static MemberMissionResDTO.CreateResultDTO toCreateResultDTO(MemberMission memberMission){
        return MemberMissionResDTO.CreateResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionReqDTO.CreateDTO request, Member member, Mission mission){
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(MissionStatus.IN_PROGRESS) //초기 상태를 도전 중으로
                .build();
    }
}
