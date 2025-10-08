package com.example.umc9th.domain.user;


import com.example.umc9th.domain.store.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        //한 명의 유저가 동일한 카테고리를 중복으로 선호할 수 없도록 유니크 제약조건 설정
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_category_unique",
                        columnNames = {"user_id", "category_id"}
                )
        }
)
public class UserFoodPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
