package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.member.enums.PointTransactionType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PointTransaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointTransactionType pointTransactionType;

    private String description;

    private static PointTransaction createPointTransaction(Member member, Integer amount, PointTransactionType type, String description){
        // 금액이 양수인지 검증
        if(amount <=0){
            throw new IllegalArgumentException("포인트 거래 금액은 양수여야 합니다.");
        }

        // Member 총 포인트 업데이트
        if(type == PointTransactionType.EARN){
            member.addPoint(amount);
        }else{
            member.usePoint(amount);
        }

        //PointTransaction 객체 생성 및 반환
        return PointTransaction.builder()
                .member(member)
                .amount(amount)
                .pointTransactionType(type)
                .description(description)
                .build();
    }

}
