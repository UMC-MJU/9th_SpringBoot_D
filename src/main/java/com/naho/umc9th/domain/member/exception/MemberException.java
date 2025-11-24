package com.naho.umc9th.domain.member.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseErrorCode;
import com.naho.umc9th.domain.common.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code){
        super(code);
    }
}
