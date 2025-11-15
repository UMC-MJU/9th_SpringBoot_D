package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    //공통 성공
    SUCCESS(HttpStatus.OK, "S000", "요청이 성공적으로 처리되었습니다."),

    //리뷰 관련 성공
    REVIEW_CREATED(HttpStatus.CREATED, "S001", "리뷰가 성공적으로 작성되었습니다."),
    REVIEW_UPDATED(HttpStatus.OK, "S002", "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_DELETED(HttpStatus.OK, "S003", "리뷰가 성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
