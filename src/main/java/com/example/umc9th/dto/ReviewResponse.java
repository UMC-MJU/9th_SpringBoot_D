package com.example.umc9th.dto;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private String content;
    private Float star;
    private ReplyInfo reply;

    private Integer rating;
    private LocalDateTime createdAt;
    private StoreInfo store;
    private List<PhotoInfo> photos;
    

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreInfo {
        private Long storeId;
        private String storeName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhotoInfo {
        private Long photoId;
        private String imageUrl;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyInfo {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
    }
}
