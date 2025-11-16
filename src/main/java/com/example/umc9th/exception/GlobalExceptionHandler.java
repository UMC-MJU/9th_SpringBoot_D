package com.example.umc9th.exception;


import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 커스텀 비즈니스 예외 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException e, HttpServletRequest request) {
        
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.onFailure(errorCode);
        
        log.warn("BusinessException: {} - {}", errorCode.getCode(), e.getMessage());
        
        return ResponseEntity.status(errorCode.getStatus())
            .body(response);
    }
    
    /**
     * IllegalArgumentException 처리
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request) {
        
        ApiResponse<Void> response = ApiResponse.onFailure(ErrorCode.INVALID_INPUT_VALUE);
        
        log.warn("IllegalArgumentException: {}", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(response);
    }
    
    /**
     * @Valid 검증 실패 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        
        ApiResponse<Void> response = ApiResponse.onFailure(ErrorCode.INVALID_INPUT_VALUE);
        
        
        log.warn("ValidationException: {}", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(response);
    }
    
    /**
     * 기타 예외 처리 (최종 예외 핸들러)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception e, HttpServletRequest request) {
        
        ApiResponse<Void> response = ApiResponse.onFailure(ErrorCode.INTERNAL_SERVER_ERROR);
        
        log.error("Unexpected error occurred: ", e);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(response);
    }
}