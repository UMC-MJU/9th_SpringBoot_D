package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewDto;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewException;
import com.example.umc9th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.global.entity.QAddress;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<ReviewDto> searchReview(String query, String type, Pageable pageable) {
        QReview review = QReview.review;
        QAddress address = QAddress.address1;

        BooleanBuilder builder = new BooleanBuilder();

        if(type != null) {
            if(query == null) {
                throw new ReviewException(ReviewErrorCode.QUERY_EXCEPTION);
            }

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
        Page<ReviewDto> reviewPage = reviewRepository.searchReview(builder, pageable);
        return reviewPage;
//                .stream()
//                .map(ReviewDto::fromEntity) // 임시 변환 메서드 사용 (실제로는 Mapper 사용 권장)
//                .collect(Collectors.toList());
    }
    @Override
    public Page<ReviewDto> searchReviewByMemberId(Long memberId, String query, String type, Pageable pageable) {

        if (memberId == null || memberId <= 0) {
            throw new IllegalArgumentException("유효하지 않은 Member ID입니다.");
        }

        QMember member = QMember.member;
        QStore store = QStore.store;
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(member.id.eq(memberId));

        if(type != null) {
            if(query == null) {
                throw new ReviewException(ReviewErrorCode.QUERY_EXCEPTION);
            }
            if (type.equals("name")) {
                builder.and(store.name.contains((query)));
            }

            if (type.equals("star")) {
                builder.and(review.star.goe(Float.parseFloat(query)));
            }

            if (type.equals("both")) {
                String[] queryParts = query.split("&");
                if (queryParts.length < 2) {
                    // 쿼리 형식이 올바르지 않을 경우 예외 처리
                    throw new IllegalArgumentException("type=both를 사용하려면 쿼리 파라미터는 '이름&별점' 형식으로 '&' 기호를 포함해야 합니다.");
                }

                String firstQuery = queryParts[0];
                String secondQuery = queryParts[1];

                builder.and(store.name.contains((firstQuery)));
                builder.and(review.star.goe(Float.parseFloat(secondQuery)));
            }
        }

        Page<ReviewDto> reviewPage = reviewRepository.searchReview(builder, pageable); // 반환 되는 값이 같기 때문에 predicate로만 처리
        return reviewPage;
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
        String storeName, Integer page
    ){
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewListDTO findReviewByStoreId(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewListDTO findReviewByMemberId(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByMember(member, pageRequest);

        return ReviewConverter.toReviewListDTO(result);
    }
}
