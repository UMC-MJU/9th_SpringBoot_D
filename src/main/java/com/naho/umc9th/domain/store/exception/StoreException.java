package com.naho.umc9th.domain.store.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code){
        super(code);
    }
}
