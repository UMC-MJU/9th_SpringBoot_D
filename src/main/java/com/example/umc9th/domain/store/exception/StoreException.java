package com.example.umc9th.domain.store.exception;

import com.example.umc9th.exception.BusinessException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;

public class StoreException extends BusinessException{
    public StoreException(ErrorCode errorCode) {
        super(errorCode);
    }

    public StoreException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
