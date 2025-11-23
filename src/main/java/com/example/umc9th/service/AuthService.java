package com.example.umc9th.service;

import com.example.umc9th.domain.common.Address;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.MemberFoodPreference;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.Role;
import com.example.umc9th.domain.store.Category;
import com.example.umc9th.dto.auth.SignUpRequest;
import com.example.umc9th.dto.auth.SignUpResponse;
import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;
import com.example.umc9th.repository.common.AddressRepository;
import com.example.umc9th.repository.member.MemberRepository;
import com.example.umc9th.repository.store.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        //닉네임 중복 여부 검사
        if (memberRepository.existsByNickName(request.nickName())) {
            throw new BusinessException(ErrorCode.MEMBER002);
        }
        //주소 ID로 실제 Address 엔티티 조회
        Address address = addressRepository.findById(request.addressId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS001));

        //선호 카테고리 ID 중복 제거 및 순서 유지
        LinkedHashSet<Long> categoryIdSet = request.preferredCategoryIds().stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        List<Long> distinctCategoryIds = List.copyOf(categoryIdSet);

        //ID 리스트로 Category 엔티티 미리 로딩하여 맵 구성
        List<Category> categories = categoryRepository.findAllById(distinctCategoryIds);
        Map<Long, Category> categoryMap = categories.stream()
                .collect(Collectors.toMap(Category::getId, Function.identity()));

        //입력 값에 성별이 없으면 NONE으로 기본값 처리
        Gender gender = request.gender() != null ? request.gender() : Gender.NONE;

        //회원 기본 정보 빌드
        Member member = Member.builder()
                .nickName(request.nickName())
                .gender(gender)
                .birthday(request.birthday())
                .address(address)
                .role(Role.CUSTOMER)
                .build();

        //선호 카테고리 엔티티 생성 후 회원 연관관계에 추가
        distinctCategoryIds.stream()
                .map(categoryMap::get)
                .forEach(category -> member.getMemberFoodPreferences().add(
                        MemberFoodPreference.builder()
                                .member(member)
                                .category(category)
                                .build()
                ));

        //회원 저장 및 선호 카테고리 이름 추출
        Member savedMember = memberRepository.save(member);

        List<String> preferredCategoryNames = distinctCategoryIds.stream()
                .map(categoryMap::get)
                .map(Category::getName)
                .toList();

        return new SignUpResponse(
                savedMember.getId(),
                savedMember.getNickName(),
                address.getAddressName(),
                preferredCategoryNames
        );
    }
}
