package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.member.board.Inquiry;
import com.example.umc9th.domain.member.enums.MemberStatus;
import com.example.umc9th.domain.member.enums.SocialType;
import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.Role;
import com.example.umc9th.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(unique=true)
    private String phone;

    private LocalDateTime phoneVerifiedAt;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(unique = true)
    private String socialId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private com.example.umc9th.domain.common.Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Integer point = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status = MemberStatus.ACTIVE;

    private LocalDateTime inactiveAt;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PointTransaction> pointTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFoodPreference> memberFoodPreferences = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerms> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Store> stores = new ArrayList<>();

    //회원 탈퇴 요청
    public void deactivate(){
        this.status = MemberStatus.INACTIVE;
        this.inactiveAt = LocalDateTime.now();
    }

    //회원 복귀
    public void reactivate() {
        if (this.status != MemberStatus.INACTIVE) {
            throw new IllegalStateException("휴면 상태의 회원만 복귀할 수 있습니다.");
        }
        this.status = MemberStatus.ACTIVE;
        this.inactiveAt = null;
    }

    //개인정보 영구 파기
    public void anonymize() {
        this.nickName = "탈퇴한 회원_" + this.id;
        this.phone = null;
        this.phoneVerifiedAt = null;
        this.socialType = null;
        this.socialId = null;
        this.gender = null;
        this.birthday = null;
        this.point = 0;
    }

    public void addPoint(Integer amount){
        this.point += amount;
    }

    public void usePoint(Integer amount){
        if(this.point < amount){
            throw new IllegalStateException("포인트가 부족합니다.");
        }
        this.point -= amount;
    }

}
