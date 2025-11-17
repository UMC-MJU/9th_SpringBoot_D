package com.naho.umc9th.domain.common.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // (가장 일반적인 성공 코드)
    _OK(HttpStatus.OK, "COMMOM200_1", "요청에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
