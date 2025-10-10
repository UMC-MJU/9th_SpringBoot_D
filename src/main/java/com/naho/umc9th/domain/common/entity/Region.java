package com.naho.umc9th.domain.common.entity;

import com.naho.umc9th.domain.common.enums.District;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    @Builder.Default
    private String city = "SEOUL";

    @Column(name = "district")
    @Enumerated(EnumType.STRING)
    private District district;
}
