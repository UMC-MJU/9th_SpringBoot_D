package com.example.umc9th.global.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zonecode", nullable = false)
    private int zonecode;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "sido", length = 100, nullable = false)
    private String sido;

    @Column(name = "sigungu", length = 100, nullable = false)
    private String sigungu;

    @Column(name = "bname", length = 100, nullable = false)
    private String bname;

    @Column(name = "roadname", length = 100, nullable = false)
    private String roadname;

    @Column(name = "detail", length = 100, nullable = true)
    private String detail;
}
