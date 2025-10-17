package com.example.umc9th.domain.common;


import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.domain.store.Store;
import com.example.umc9th.domain.member.board.Inquiry;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotoType photoType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    //Photo 객체를 생성하는 시점에 store, review, inquiry 중 정확히 하나만 관계를 맺도록 강제
    @Builder
    public Photo(String imageUrl, PhotoType photoType, Store store, Review review, Inquiry inquiry) {
        // store, review, inquiry 중 하나만 값이 있는지 검증
        if ((store != null && review == null && inquiry == null) ||
                (store == null && review != null && inquiry == null) ||
                (store == null && review == null && inquiry != null)) {

            this.imageUrl = imageUrl;
            this.photoType = photoType;
            this.store = store;
            this.review = review;
            this.inquiry = inquiry;
        } else {
            // 예외 발생 혹은 로깅
            throw new IllegalArgumentException("Photo must be linked to exactly one of Store, Review, or Inquiry.");
        }
    }

}
