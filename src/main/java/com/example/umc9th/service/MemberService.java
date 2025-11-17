package com.example.umc9th.service;

import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.NotificationSetting;
import com.example.umc9th.dto.MyPageResponse;
import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;
import com.example.umc9th.repository.member.MemberRepository;
import com.example.umc9th.repository.member.NotificationSettingRepository;
import com.example.umc9th.repository.member.board.InquiryRepository;
import com.example.umc9th.repository.member.board.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final InquiryRepository inquiryRepository;
    private final NotificationSettingRepository notificationSettingRepository;
    
    public MyPageResponse getMyPage(Long memberId) {
        // 1. 회원 기본 정보 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER001));
        
        // 2. 작성한 리뷰 개수 조회
        long reviewCount = reviewRepository.countByMemberId(memberId);
        
        // 3. 작성한 문의 개수 조회
        long inquiryCount = inquiryRepository.countByMemberId(memberId);
        
        // 4. 알림 설정 조회 (없을 수 있음)
        NotificationSetting notificationSetting = notificationSettingRepository
            .findByMemberId(memberId)
            .orElse(null);
        
        // 5. DTO로 변환하여 반환
        return MyPageResponse.builder()
            .nickName(member.getNickName())
            .email(member.getSocialId())  // 소셜 로그인 이메일
            .phone(member.getPhone())
            .phoneVerified(member.getPhoneVerifiedAt() != null)
            .point(member.getPoint())
            .reviewCount(reviewCount)
            .inquiryCount(inquiryCount)
            .eventAlerts(notificationSetting != null ? notificationSetting.getEventAlerts() : true)
            .reviewReplyAlerts(notificationSetting != null ? notificationSetting.getReviewReplyAlerts() : true)
            .inquiryReplyAlerts(notificationSetting != null ? notificationSetting.getInquiryReplyAlerts() : true)
            .build();
    }
}
