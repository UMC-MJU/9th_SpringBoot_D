package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_FOUND(HttpStatus.OK,
            "STORE200_1",
            "가게 정보 조회 성공.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
