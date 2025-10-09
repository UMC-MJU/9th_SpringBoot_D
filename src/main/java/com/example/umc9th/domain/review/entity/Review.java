package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", length = 300, nullable = false)
    private String comment;

    @Column(name = "star", nullable = false)
    private Integer star;

    @OneToMany(mappedBy = "review")
    private List<ReviewReply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> imageList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
