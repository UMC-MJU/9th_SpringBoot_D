package com.example.umc9th.domain.store.dto;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.Address;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreDto {
    private Long storeId;
    private String name;
    private Address address;

    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }

    public Store toEntity() {
        return Store.builder()
                .name(this.name)
                .address(this.address)
                .build();
    }
}