package com.naho.umc9th.domain.member.service.query;

import com.naho.umc9th.domain.common.auth.CustomUserDetails;
import com.naho.umc9th.domain.common.jwt.JwtUtil;
import com.naho.umc9th.domain.member.converter.MemberConverter;
import com.naho.umc9th.domain.member.dto.MemberReqDto;
import com.naho.umc9th.domain.member.dto.MemberResDTO;
import com.naho.umc9th.domain.member.entity.Member;
import com.naho.umc9th.domain.member.exception.MemberException;
import com.naho.umc9th.domain.member.exception.code.MemberErrorCode;
import com.naho.umc9th.domain.member.repository.MemberRepository;
import com.naho.umc9th.domain.review.entity.Review;
import com.naho.umc9th.domain.review.repository.ReviewRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;


    @Override
    public MemberResDTO.ReviewPreViewListDTO getReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 서비스에서는 0부터 시작하는 페이지 번호 그대로 사용(Controller에서 변환해서 넘겨줌)
        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));

        return MemberConverter.toReviewPreViewListDTO(memberPage);
    }

    @Override
    public MemberResDTO.LoginDTO login(
            @Valid MemberReqDto.LoginDTO dto
    ) {
        //Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        //비밀번호 검증
        if(!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }


}
