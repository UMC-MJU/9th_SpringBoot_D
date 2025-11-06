package com.example.umc9th.domain.store.dto;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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