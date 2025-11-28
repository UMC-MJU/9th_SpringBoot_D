package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    // 공통 성공
    COMMON200(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),

    // 멤버 관련 성공
    MEMBER200(HttpStatus.OK, "MEMBER200", "회원 정보 조회가 완료되었습니다."),
    AUTH201(HttpStatus.CREATED, "AUTH201", "회원 가입이 완료되었습니다."),

    // 가게 관련 성공
    STORE200(HttpStatus.OK, "STORE200", "가게 정보 조회가 완료되었습니다."),

    // 리뷰 관련 성공
    REVIEW200(HttpStatus.OK, "REVIEW200", "리뷰 조회가 완료되었습니다."),
    REVIEW201(HttpStatus.CREATED, "REVIEW201", "리뷰가 성공적으로 작성되었습니다."),
    REVIEW202(HttpStatus.OK, "REVIEW202", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW203(HttpStatus.OK, "REVIEW203", "리뷰가 성공적으로 삭제되었습니다."),

    // 미션 관련 성공
    MISSION201(HttpStatus.CREATED, "MISSION201", "미션 도전이 시작되었습니다."),
    MISSION200(HttpStatus.OK, "MISSION200", "미션 목록 조회가 완료되었습니다."),

    // 테스트 관련 성공
    TEST200(HttpStatus.OK, "TEST200", "테스트 요청이 성공적으로 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
