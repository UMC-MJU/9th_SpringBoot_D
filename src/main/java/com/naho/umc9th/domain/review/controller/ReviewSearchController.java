package com.naho.umc9th.domain.review.controller;


import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewSearchController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/search")
    public List<Review> searchReview(
            @RequestParam String query, //query: "안암동"
            @RequestParam String type //type : "location"
    ){

        //서비스에게 요청
        List<Review> result = reviewQueryService.searchReview(query, type);
        return result;
    }
}
