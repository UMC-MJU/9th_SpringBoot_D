package com.example.umc9th.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    // 공통 에러 (400번대)
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E001", "잘못된 입력값입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "E002", "타입이 올바르지 않습니다."),
    
    // 리뷰 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "R001", "존재하지 않는 회원입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "R002", "존재하지 않는 가게입니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "R003", "이미 리뷰를 작성한 가게입니다."),
    INVALID_RATING(HttpStatus.BAD_REQUEST, "R004", "평점은 1~5 사이의 정수여야 합니다."),
    
    // 서버 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 내부 오류가 발생했습니다.");
    
    private final HttpStatus status;
    private final String code;
    private final String message;
}