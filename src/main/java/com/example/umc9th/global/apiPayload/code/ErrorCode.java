package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    //공통 에러
    COMMON000(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON000", "서버 내부 오류가 발생했습니다."),
    COMMON001(HttpStatus.BAD_REQUEST, "COMMON001", "잘못된 입력값입니다."),
    COMMON002(HttpStatus.BAD_REQUEST, "COMMON002", "타입이 올바르지 않습니다."),

    //멤버 관련 에러
    MEMBER001(HttpStatus.NOT_FOUND, "MEMBER001", "존재하지 않는 회원입니다."),

    //가게 관련 에러
    STORE001(HttpStatus.NOT_FOUND, "STORE001", "존재하지 않는 가게입니다."),

    //리뷰 관련 에러
    REVIEW001(HttpStatus.CONFLICT, "REVIEW001", "이미 리뷰를 작성한 가게입니다."),
    REVIEW4001(HttpStatus.BAD_REQUEST, "REVIEW4001", "평점은 1~5 사이의 정수여야 합니다."),

    //테스트 관련 에러
    TEST001(HttpStatus.BAD_REQUEST, "TEST001", "이거는 테스트");
    
    private final HttpStatus status;
    private final String code;
    private final String message;
}