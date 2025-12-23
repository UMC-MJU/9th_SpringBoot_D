package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.NotificationType;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "body", length = 100, nullable = false)
    private String body;

    @Column(name = "dtype", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType dtype;

    @Column(name = "is_checked", nullable = true)
    @Builder.Default
    private Boolean isChecked = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;
}
