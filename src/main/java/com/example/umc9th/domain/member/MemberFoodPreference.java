package com.example.umc9th.domain.member;


import com.example.umc9th.domain.store.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        //한 명의 유저가 동일한 카테고리를 중복으로 선호할 수 없도록 유니크 제약조건 설정
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "member_category_unique",
                        columnNames = {"member_id", "category_id"}
                )
        }
)
public class MemberFoodPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
