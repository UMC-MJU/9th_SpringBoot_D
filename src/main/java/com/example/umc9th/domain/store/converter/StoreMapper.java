package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.StoreDto;
import com.example.umc9th.domain.store.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    @Mapping(target = "storeId", source = "id")
    StoreDto toDto(Store store);

    @Mapping(target = "id", source = "storeId")
    Store toEntity(StoreDto storeDto);
}