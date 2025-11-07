package com.example.umc9th.domain.review.service;


import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.global.entity.QAddress;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    //private final ReviewMapper reviewMapper;

    public Page<ReviewDto> searchReview(String query, String type, Pageable pageable) {
        QReview review = QReview.review;
        QAddress address = QAddress.address1;

        BooleanBuilder builder = new BooleanBuilder();

        if(type != null) {
            if (type.equals("address")) {
                builder.and(address.sido.contains((query)));
            }

            if (type.equals("star")) {
                builder.and(review.star.goe(Float.parseFloat(query)));
            }

            if (type.equals("both")) {
                String firstQuery = query.split("&")[0];
                String secondQuery = query.split("&")[1];

                builder.and(address.sido.contains((firstQuery)));
                builder.and(review.star.goe(Float.parseFloat(secondQuery)));
            }
        }
        Page<ReviewDto> reviewList = reviewRepository.searchReview(builder, pageable);
        return reviewList;
//                .stream()
//                .map(ReviewDto::fromEntity) // 임시 변환 메서드 사용 (실제로는 Mapper 사용 권장)
//                .collect(Collectors.toList());
    }
}
