package com.example.umc9th.domain.member;

import com.example.umc9th.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY) // 성능 최적화: 필요할 때만 로드
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private boolean isMandatory;
}
