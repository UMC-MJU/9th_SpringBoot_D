package com.example.umc9th.global.apiPayload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.example.umc9th.global.apiPayload.code.SuccessCode;
import com.example.umc9th.global.apiPayload.code.ErrorCode;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean isSuccess;
    private String code;
    private String message;
    private T result;

    //성공 응답 생성 메서드
    public static <T> ApiResponse<T> onSuccess(SuccessCode successCode, T result){
        return new ApiResponse<>(true, successCode.getCode(), successCode.getMessage(), result);
    }


    //실패 응답
    public static <T> ApiResponse<T> onFailure(ErrorCode errorCode){
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }
}
