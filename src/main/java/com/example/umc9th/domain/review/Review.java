package com.example.umc9th.domain.review;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.common.Photo;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.user.PointTransaction;
import com.example.umc9th.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //리뷰는 특정 포인트 거래와 일대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_transaction_id", unique = true)
    private PointTransaction pointTransaction;

    @Lob
    private String content;

    @Column(nullable = false)
    private Integer rating;

    //리뷰가 삭제되면 관련된 사진도 함께 DB에서 삭제
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    //리뷰에 달린 사장님 답변과의 1:1관계
    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReviewAnswer reviewAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
