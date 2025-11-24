package com.example.umc9th.domain.mission.converter.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MemberMissionConverter {
    public static MemberMissionResDTO.AddMemberMissionDTO toAddDTO(MemberMission memberMission) {
        return MemberMissionResDTO.AddMemberMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .state(memberMission.getState())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(
            MemberMissionReqDTO.AddMemberMissionDTO dto,
            Member member,
            Mission mission
    ){
        return MemberMission.builder()
                .state(dto.state())
                .member(member)
                .mission(mission)
                .build();
    }
}
