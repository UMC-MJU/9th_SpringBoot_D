package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table( //한 회원이 동일한 약관에 여러 번 동의하는 데이터가 중복으로 쌓일 가능성 배제를 위한 복합 유니크 제약조건 추가
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_terms_unique",
                        columnNames = {"member_id", "term_id"}
                )
        }
)
public class MemberTerms extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "term_id")
    private Terms terms;
}
