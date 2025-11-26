package com.naho.umc9th.domain.review.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code){
        super(code);
    }
}
