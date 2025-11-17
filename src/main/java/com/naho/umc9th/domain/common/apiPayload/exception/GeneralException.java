package com.naho.umc9th.domain.common.apiPayload.exception;

import com.naho.umc9th.domain.common.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
}
