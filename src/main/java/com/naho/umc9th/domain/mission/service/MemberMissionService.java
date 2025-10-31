package com.naho.umc9th.domain.mission.service;

import com.naho.umc9th.domain.mission.dto.MemberMissionDto;
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

    public MemberMissionService(MemberMissionRepository memberMissionRepository) {
        this.memberMissionRepository = memberMissionRepository;
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
}
