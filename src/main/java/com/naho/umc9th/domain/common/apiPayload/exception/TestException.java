package com.naho.umc9th.domain.common.apiPayload.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseErrorCode;

public class TestException extends GeneralException{
    public TestException(BaseErrorCode code){
        super(code);
    }
}
