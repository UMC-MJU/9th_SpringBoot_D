package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.Status;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.MissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission challengeMission(Long memberId, Long missionId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        if (mission.getDeadline().isBefore(LocalDateTime.now())) {
            throw new MissionException(MissionErrorCode.DEADLINE_EXPIRED);
        }

        if (memberMissionRepository.existsByMember_IdAndMission_Id(memberId, missionId))
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_CHALLENGED);

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(Status.ONGOING)
                .build();

        return memberMissionRepository.save(memberMission);
    }


}
