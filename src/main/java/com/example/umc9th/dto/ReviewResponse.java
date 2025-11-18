package com.example.umc9th.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewResponse(
    Long id,
    String content,
    Float star,
    ReplyInfo reply,
    Integer rating,
    LocalDateTime createdAt,
    StoreInfo store,
    List<PhotoInfo> photos
) {

    public record StoreInfo(Long storeId, String storeName) {}

    public record PhotoInfo(Long photoId, String imageUrl) {}

    public record ReplyInfo(Long id, String content, LocalDateTime createdAt) {}
}
