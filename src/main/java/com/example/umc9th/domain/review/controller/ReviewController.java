package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.entity.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    public ReviewController(ReviewQueryService reviewQueryService) {
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping("/reviews/search")
    public Page<ReviewDto> searchReview(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String type,
            PageRequest pageRequest
    ){
        Pageable pageable = pageRequest.of();

        Page<ReviewDto> result = reviewQueryService.searchReview(query, type, pageable);
        return result;
    }
}
