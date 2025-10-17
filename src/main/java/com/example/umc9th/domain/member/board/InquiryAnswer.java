package com.example.umc9th.domain.member.board;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InquiryAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false, unique = true)
    private Inquiry inquiry;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name= "member_id")
    private Member admin;

    @Lob
    @Basic(fetch = FetchType.LAZY) // 성능 최적화: 필요할 때만 로드
    @Column(nullable = false)
    private String content;
}
