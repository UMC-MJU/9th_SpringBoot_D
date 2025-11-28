package com.example.umc9th.service;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.MemberMission;
import com.example.umc9th.domain.member.enums.MissionStatus;
import com.example.umc9th.domain.store.Mission;
import com.example.umc9th.dto.mission.ChallengeMissionRequest;
import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;
import com.example.umc9th.repository.member.MemberMissionRepository;
import com.example.umc9th.repository.member.MemberRepository;
import com.example.umc9th.repository.store.MissionRepository;
import lombok.RequiredArgsConstructor;
import com.example.umc9th.converter.MissionConverter;
import com.example.umc9th.dto.MyMissionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

        private final MissionRepository missionRepository;
        private final MemberMissionRepository memberMissionRepository;
        private final MemberRepository memberRepository;

        @Transactional
        public MemberMission challengeMission(ChallengeMissionRequest request) {
                // 하드 코딩
                Member member = memberRepository.findFirstByOrderByIdAsc()
                                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER001));

                // 미션 존재 여부 확인
                Mission mission = missionRepository.findById(request.missionId())
                                .orElseThrow(() -> new BusinessException(ErrorCode.MISSION001));

                // 미션이 도전 가능한 상태인지 확인
                if (!mission.isAvailable()) {
                        throw new BusinessException(ErrorCode.MISSION002);
                }

                // 이미 도전한 미션인지 확인
                if (memberMissionRepository.existsByMemberIdAndMissionId(member.getId(), request.missionId())) {
                        throw new BusinessException(ErrorCode.MISSION003);
                }

                MemberMission memberMission = MemberMission.builder()
                                .member(member)
                                .mission(mission)
                                .status(MissionStatus.CHALLENGING)
                                .build();

                return memberMissionRepository.save(memberMission);

        }

        @Transactional(readOnly = true)
        public Page<MyMissionResponseDTO> getMyChallengingMissions(Long memberId, Pageable pageable) {
                Page<MemberMission> memberMissionPage = memberMissionRepository.findMyMissionsByStatuses(
                                memberId,
                                List.of(MissionStatus.CHALLENGING),
                                pageable);

                List<MyMissionResponseDTO> myMissionResponseDTOS = memberMissionPage.getContent().stream()
                                .map(MissionConverter::toMyMissionResponseDTO)
                                .collect(Collectors.toList());

                return new PageImpl<>(
                                myMissionResponseDTOS,
                                pageable,
                                memberMissionPage.getTotalElements());
        }

        @Transactional
        public void completeMission(Long memberMissionId) {
                MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                                .orElseThrow(() -> new BusinessException(ErrorCode.MISSION001));

                memberMission.complete();
        }

        @Transactional(readOnly = true)
        public Page<MyMissionResponseDTO> getCompletedMissions(Long memberId, Pageable pageable) {
                Page<MemberMission> memberMissionPage = memberMissionRepository.findMyMissionsByStatuses(
                                memberId,
                                List.of(MissionStatus.COMPLETED),
                                pageable);

                List<MyMissionResponseDTO> myMissionResponseDTOS = memberMissionPage.getContent().stream()
                                .map(MissionConverter::toMyMissionResponseDTO)
                                .collect(Collectors.toList());

                return new PageImpl<>(
                                myMissionResponseDTOS,
                                pageable,
                                memberMissionPage.getTotalElements());
        }
}
