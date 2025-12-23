package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberTerm;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.global.auth.enums.Role;
import com.example.umc9th.global.entity.Address;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity //엔티티 명시
@Builder //lombok 어노테이션, 빌더 디자인 패턴 자동 구현
@NoArgsConstructor(access = AccessLevel.PROTECTED) // lombok 어노테이션, 파라미터 없는 기본 생성자 자동 생성,접근 제한자 Protected 설정
@AllArgsConstructor(access = AccessLevel.PRIVATE) // lombok 어노테이션, 모든 필드 값의 생성자를 자동 생성, 접든 제한자 Private 설정
@Getter
@Table(name = "member") // 테이블 이름 설정
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone", nullable = true)
    @Builder.Default
    private String phone = null;

    @Column(name = "name", length = 20,  nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "social_type", nullable = true)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialType socialType = null;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<NotificationSetting> notificationSettingList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<ConversionHistory> conversionHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}
