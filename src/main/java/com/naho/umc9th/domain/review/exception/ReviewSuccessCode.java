package com.naho.umc9th.domain.review.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    _OK(HttpStatus.OK, "Reivew200_1", "리뷰 검색에 성공하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
