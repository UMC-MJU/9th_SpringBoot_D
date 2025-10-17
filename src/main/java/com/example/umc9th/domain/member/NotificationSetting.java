package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NotificationSetting extends BaseTimeEntity {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    //기본적으로 모든 알림을 받는 것을 기본 값으로 설정
    @Builder.Default
    private Boolean eventAlerts = true;
    
    @Builder.Default
    private Boolean reviewReplyAlerts = true;
    
    @Builder.Default
    private Boolean inquiryReplyAlerts = true;
}
