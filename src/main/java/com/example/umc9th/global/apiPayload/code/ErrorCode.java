package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통 에러
    COMMON000(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON000", "서버 내부 오류가 발생했습니다."),
    COMMON001(HttpStatus.BAD_REQUEST, "COMMON001", "잘못된 입력값입니다."),
    COMMON002(HttpStatus.BAD_REQUEST, "COMMON002", "타입이 올바르지 않습니다."),
    PAGE_NOT_VALID(HttpStatus.BAD_REQUEST, "PAGE001", "페이지 번호는 0보다 커야 합니다."),

    // 멤버 관련 에러
    MEMBER001(HttpStatus.NOT_FOUND, "MEMBER001", "존재하지 않는 회원입니다."),
    MEMBER002(HttpStatus.CONFLICT, "MEMBER002", "이미 사용 중인 닉네임입니다."),

    // 주소 관련 에러
    ADDRESS001(HttpStatus.NOT_FOUND, "ADDRESS001", "존재하지 않는 주소입니다."),

    // 카테고리 관련 에러
    CATEGORY001(HttpStatus.NOT_FOUND, "CATEGORY001", "존재하지 않는 카테고리입니다."),

    // 가게 관련 에러
    STORE001(HttpStatus.NOT_FOUND, "STORE001", "존재하지 않는 가게입니다."),

    // 리뷰 관련 에러
    REVIEW001(HttpStatus.CONFLICT, "REVIEW001", "이미 리뷰를 작성한 가게입니다."),
    REVIEW4001(HttpStatus.BAD_REQUEST, "REVIEW4001", "평점은 1~5 사이의 정수여야 합니다."),

    // 미션 관련 에러
    MISSION001(HttpStatus.NOT_FOUND, "MISSION001", "존재하지 않는 미션입니다."),
    MISSION002(HttpStatus.BAD_REQUEST, "MISSION002", "도전 가능한 기간이 아닙니다."),
    MISSION003(HttpStatus.CONFLICT, "MISSION003", "이미 도전한 미션입니다."),

    // 테스트 관련 에러
    TEST001(HttpStatus.BAD_REQUEST, "TEST001", "이거는 테스트");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
