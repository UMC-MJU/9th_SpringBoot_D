package com.example.umc9th.domain.test.exception;

import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;

public class TestException extends BusinessException{
    public TestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
