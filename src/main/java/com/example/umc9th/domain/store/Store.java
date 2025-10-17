package com.example.umc9th.domain.store;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.common.Photo;
import com.example.umc9th.domain.member.Member;
import com.example.umc9th.domain.member.board.Review;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private String name;

    @Lob //긴 텍스트를 위한 설정
    private String description;

    @Column(nullable = false)
    private Integer minOrderPrice;

    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private com.example.umc9th.domain.common.Address address;

    @Column(name = "average_rating")
    private Float averageRating;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    //가게의 평균 평점 계산 로직
    public float getAverageRating(){
        // 리뷰가 없는 경우 0점 반환 (0으로 나누기 방지)
        if (reviews == null || reviews.isEmpty()) {
            return 0.0f;
        }

        // 모든 리뷰의 평점을 합산
        double sum = reviews.stream()
                .mapToDouble(Review::getRating)
                .sum();

        // 평균을 계산하여 반환
        return (float) (sum / reviews.size());
    }
}
