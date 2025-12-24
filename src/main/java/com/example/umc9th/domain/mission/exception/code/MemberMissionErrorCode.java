package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberMissionErrorCode implements BaseErrorCode {

    ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST,
            "MEMBERMISSION400_1",
            "이미 도전한 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

