package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findMyReviews(
            Long memberId,
            String storeName,
            Integer star,
            Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 필수: memberId
        builder.and(review.member.id.eq(memberId));

        // optional: storeName
        if (storeName != null && !storeName.isEmpty()) {
            builder.and(review.store.name.containsIgnoreCase(storeName));
        }

        // optional: star
        if (star != null) {
            if (star == 5) {
                builder.and(review.star.eq(5.0f));
            } else {
                float min = star.floatValue();
                float max = star + 0.9f;
                builder.and(review.star.between(min, max));
            }
        }

        // 삭제된 리뷰 제외
        builder.and(review.deletedAt.isNull());

        // 검색 수행
        Page<Review> result = reviewRepository.searchReviews(builder, pageRequest);

        // DTO 변환
        return ReviewConverter.toReviewPreviewListDTO(result);
    }


    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page -1, 10);
        Page<Review> result = reviewRepository.findAllByStoreAndDeletedAtIsNull(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
