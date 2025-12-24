package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.enums.*;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "nickname", length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "phone_num", nullable = false, length = 15)
    private String phoneNum;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "social_uid", unique = true, length = 100)
    private String social_uid;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType social_type = SocialType.LOCAL;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.active;

    @Column(name = "inactive_at")
    private LocalDateTime inactiveAt;

    // 회원이 참여한 미션 목록 — 회원 삭제 시 미션 매핑도 함께 삭제
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();

    // 회원의 선호 음식 — 회원 삭제 시 연결된 유저 선호 음식 데이터도 함께 삭제
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberFood> memberFoods = new ArrayList<>();
}
