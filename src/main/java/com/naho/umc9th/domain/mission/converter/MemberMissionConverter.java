package com.naho.umc9th.domain.mission.converter;

import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.naho.umc9th.domain.mission.entity.MemberMission;
import com.naho.umc9th.domain.mission.entity.Mission;
import com.naho.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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


    public static MemberMissionResDTO.MemberMissionPreViewDTO toMemberMissionPreViewDTO(MemberMission memberMission){
        return MemberMissionResDTO.MemberMissionPreViewDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionName(memberMission.getMission().getName())
                .point(memberMission.getMission().getPoint())
                .status(memberMission.getStatus().toString())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionPreViewListDTO toMemberMissionPreViewListDTO(Page<MemberMission> memberMissionList) {
        List<MemberMissionResDTO.MemberMissionPreViewDTO> missionPreViewDTOList = memberMissionList.stream()
                .map(MemberMissionConverter::toMemberMissionPreViewDTO)
                .collect(Collectors.toList());

        return MemberMissionResDTO.MemberMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }



}
