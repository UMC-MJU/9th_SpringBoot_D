package com.example.umc9th.domain.mission.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST,
            "MISSION400_2",
            "이미 마감된 미션입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "미션을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
