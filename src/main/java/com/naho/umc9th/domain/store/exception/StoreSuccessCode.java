package com.naho.umc9th.domain.store.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    _OK(HttpStatus.OK, "STORE200_1", "가게 검색에 성공하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
