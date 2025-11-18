package com.example.umc9th.global.exception;

import com.example.umc9th.global.apiPayload.code.ErrorCode;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{
    private final ErrorCode errorCode;

    //ErrorCode의 기본 메시지를 그대로 사용
    public GeneralException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    //커스텀 메시지를 설정 가능
    public GeneralException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
