package com.example.umc9th.domain.member.board.exception;

import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;

public class ReviewException extends BusinessException{
    public ReviewException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ReviewException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
