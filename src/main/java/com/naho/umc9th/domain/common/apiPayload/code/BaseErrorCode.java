package com.naho.umc9th.domain.common.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
