package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.converter.StoreMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {StoreMapper.class})
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "reviewId", source = "id")
    ReviewDto toDto(Review review);

    @Mapping(target = "id", source = "reviewId")
    Review toEntity(ReviewDto reviewDto);
}