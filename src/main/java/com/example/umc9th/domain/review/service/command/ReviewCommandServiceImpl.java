package com.example.umc9th.domain.review.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewResDTO.AddDTO addReview(
            ReviewReqDTO.AddDTO dto
    ){
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + dto.memberId()));
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new NoSuchElementException("Store not found with ID: " + dto.storeId()));
        Review review = ReviewConverter.toReview(dto,member, store);
        reviewRepository.save(review);
        return ReviewConverter.toAddDTO(review);
    }
}
