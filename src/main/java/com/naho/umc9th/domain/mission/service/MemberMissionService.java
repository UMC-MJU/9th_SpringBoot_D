package com.naho.umc9th.domain.mission.service;

import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.exception.MemberException;
import com.naho.umc9th.domain.member.exception.code.MemberErrorCode;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import com.naho.umc9th.domain.mission.converter.MemberMissionConverter;
import com.naho.umc9th.domain.mission.dto.MemberMissionDto;
import com.naho.umc9th.domain.mission.dto.MemberMissionResDTO;
import com.naho.umc9th.domain.mission.entity.MemberMission;
import com.naho.umc9th.domain.mission.enums.MissionStatus;
import com.naho.umc9th.domain.mission.repository.MemberMissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MemberMissionService(MemberMissionRepository memberMissionRepository, MemberRepository memberRepository) {
        this.memberMissionRepository = memberMissionRepository;
        this.memberRepository = memberRepository;
    }

    public Page<MemberMissionDto> getMyMissions(Long memberId) {
        List<MissionStatus> statuses = List.of(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED);

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        //Limit 10 OFFSET 10
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, sort);

        return memberMissionRepository.findMyMissionWithStatus(memberId, statuses, pageable);

    }

    public MemberMissionResDTO.MemberMissionPreViewListDTO getMyOngoingMissions(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        List<MissionStatus> statuses = List.of(MissionStatus.IN_PROGRESS);

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        // 페이징 변수 처리
        int size = 10;
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<MemberMission> missionPage = memberMissionRepository.findAllByMemberIdAndStatus(memberId, MissionStatus.IN_PROGRESS, pageable);

        return MemberMissionConverter.toMemberMissionPreViewListDTO(missionPage);

    }
}
