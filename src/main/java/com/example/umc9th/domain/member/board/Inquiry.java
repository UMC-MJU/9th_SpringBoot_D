package com.example.umc9th.domain.member.board;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.common.Photo;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.board.enums.InquiryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = true)
    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY) // 성능 최적화: 필요할 때만 로드
    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InquiryStatus status;

    @OneToMany(mappedBy = "inquiry", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    //문의가 삭제되면 답변도 함께 삭제되도록 설정
    @OneToOne(mappedBy = "inquiry", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private InquiryAnswer inquiryAnswer;
}
