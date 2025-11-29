package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> findOngoingMissions(Long memberId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        return memberMissionRepository.findOngoingMissionsByMemberId(memberId, pageRequest);
    }

}