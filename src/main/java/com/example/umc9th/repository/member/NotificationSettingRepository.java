package com.example.umc9th.repository.member;

import com.example.umc9th.domain.member.NotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {
    
    // Member ID로 알림 설정 조회(@Query 어노테이션 사용)
    @Query("SELECT ns FROM NotificationSetting ns WHERE ns.member.id = :memberId")
    Optional<NotificationSetting> findByMemberId(@Param("memberId") Long memberId);
}
