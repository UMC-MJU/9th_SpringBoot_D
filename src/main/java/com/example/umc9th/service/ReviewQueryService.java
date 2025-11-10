package com.example.umc9th.service;

import com.example.umc9th.domain.member.board.Review;
import com.example.umc9th.dto.SearchReviewRequest;
import com.example.umc9th.repository.member.board.ReviewRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchReview(SearchReviewRequest request){
        return reviewRepository.searchReview(request);
    }
}
