package com.example.umc9th.domain.member.board;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.common.Photo;
import com.example.umc9th.domain.member.MemberMission;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    //구매자 리뷰 인증
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_mission_id", unique = true)
    private MemberMission memberMission;

    @Lob
    @Basic(fetch = FetchType.LAZY) // 성능 최적화: 필요할 때만 로드
    private String content;

    @Column(nullable = false)
    private Integer rating;

    //리뷰가 삭제되면 관련된 사진도 함께 DB에서 삭제
    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    //리뷰에 달린 사장님 답변과의 1:1관계
    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private ReviewAnswer reviewAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
