package com.naho.umc9th.domain.member.service.query;

import com.naho.umc9th.domain.member.converter.MemberConverter;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.exception.MemberException;
import com.naho.umc9th.domain.member.exception.code.MemberErrorCode;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public MemberResDTO.ReviewPreViewListDTO getReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 서비스에서는 0부터 시작하는 페이지 번호 그대로 사용(Controller에서 변환해서 넘겨줌)
        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));

        return MemberConverter.toReviewPreViewListDTO(memberPage);
    }
}
