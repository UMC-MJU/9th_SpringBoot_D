package com.naho.umc9th.domain.mission.service;

import com.naho.umc9th.domain.common.apiPayload.code.GeneralErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import com.naho.umc9th.domain.mission.converter.MemberMissionConverter;
import com.naho.umc9th.domain.mission.dto.MemberMissionReqDTO;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.naho.umc9th.domain.mission.entity.MemberMission;
import com.naho.umc9th.domain.mission.entity.Mission;
import com.naho.umc9th.domain.mission.repository.MemberMissionRepository;
import com.naho.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMissionResDTO.CreateResultDTO createMemberMission(MemberMissionReqDTO.CreateDTO request){

        //미션 존재 확인
        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 멤버 존재 확인
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 엔티티 생성
        MemberMission memberMission = MemberMissionConverter.toMemberMission(request, member, mission);

        memberMissionRepository.save(memberMission);

        return MemberMissionConverter.toCreateResultDTO(memberMission);


    }

}
