package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.member.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String content;

    //알림 클릭 시 이동할 URL
    private String relatedUrl;

    @Column(nullable = false)
    private boolean isRead = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    //사용자가 알림을 읽음 처리하는 메서드
    public void markAsRead(){
        this.isRead = true;
    }
}
