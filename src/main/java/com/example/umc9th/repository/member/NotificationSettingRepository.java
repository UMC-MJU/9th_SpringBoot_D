package com.example.umc9th.repository.member;

import com.example.umc9th.domain.member.NotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {
    
    // Member ID로 알림 설정 조회 (메서드 생성 방식)
    Optional<NotificationSetting> findByMemberId(Long memberId);
}
