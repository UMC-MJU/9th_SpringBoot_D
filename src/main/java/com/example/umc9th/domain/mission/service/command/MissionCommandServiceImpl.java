package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.mapping.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.mapping.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.mapping.MemberMissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberMissionResDTO.AddMemberMissionDTO progressMission(
            MemberMissionReqDTO.AddMemberMissionDTO dto
    ){
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + dto.memberId()));
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new NoSuchElementException("Mission not found with ID: " + dto.missionId()));
        MemberMission memberMission = MemberMissionConverter.toMemberMission(dto, member, mission);
        memberMissionRepository.save(memberMission);
        return MemberMissionConverter.toAddDTO(memberMission);
    }
}
