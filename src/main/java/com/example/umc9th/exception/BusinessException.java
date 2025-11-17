package com.example.umc9th.exception;

import com.example.umc9th.global.apiPayload.code.ErrorCode;
import com.example.umc9th.global.exception.GeneralException;
import lombok.Getter;

@Getter
public class BusinessException extends GeneralException {
    
    
    
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}