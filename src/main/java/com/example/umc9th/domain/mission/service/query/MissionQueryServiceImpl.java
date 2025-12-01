package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionState;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.mapping.MemberMissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public MissionResDTO.MissionListDTO findMissionsByStoreId(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        int pageIndex = (page != null && page > 0) ? page - 1 : 0; // 페이지와 인덱스 매칭 이슈
        PageRequest pageRequest = PageRequest.of(pageIndex, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);
        return MissionConverter.toMissionListDTO(result);
    }

    @Override
    public MissionResDTO.MissionListDTO findMissionsByMemberIdAndState(Long memberId, MissionState state, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        int pageIndex = (page != null && page > 0) ? page - 1 : 0; // 페이지와 인덱스 매칭 이슈
        PageRequest pageRequest = PageRequest.of(pageIndex, 10);
        Page<MemberMission> result = memberMissionRepository.findAllByMemberAndState(member, state, pageRequest);
        return MissionConverter.toMissionListDTO(result.map(MemberMission::getMission));
    }

}
