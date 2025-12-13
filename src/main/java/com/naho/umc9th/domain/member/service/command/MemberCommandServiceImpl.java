package com.naho.umc9th.domain.member.service.command;

import com.naho.umc9th.domain.common.apiPayload.code.GeneralErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;
import com.naho.umc9th.domain.common.auth.enums.Role;
import com.naho.umc9th.domain.member.converter.MemberConverter;
import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.entity.FoodCategory;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.entity.mapping.MemberPreference;
import com.naho.umc9th.domain.member.repository.FoodCategoryRepository;
import com.naho.umc9th.domain.member.repository.MemberPreferenceRepository;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public MemberResDTO.JoinDTO signup(
            MemberReqDto.JoinDTO dto
    ){
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        //DB 적용
        memberRepository.save(member);

        //선호 음식 카테고리 저장
        if(dto.preferCategory() != null && !dto.preferCategory().isEmpty()) {
            List<MemberPreference> memberPreferenceList = new ArrayList<>();

            for(Long categoryId : dto.preferCategory()){
                // 카테고리 찾기
                FoodCategory foodCategory = foodCategoryRepository.findById(categoryId)
                        .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

                // 중간 테이블 생성
                MemberPreference memberPreference = MemberPreference.builder()
                        .member(member)
                        .foodCategory(foodCategory)
                        .build();

                memberPreferenceList.add(memberPreference);
            }

            memberPreferenceRepository.saveAll(memberPreferenceList);
        }

        return MemberConverter.toJoinDTO(member);
    }
}
