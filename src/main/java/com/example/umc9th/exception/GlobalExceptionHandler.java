package com.example.umc9th.exception;

import com.example.umc9th.dto.ErrorResponse;
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
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException e, HttpServletRequest request) {
        
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(
            errorCode.getCode(),
            e.getMessage(),
            request.getRequestURI()
        );
        
        log.warn("BusinessException: {} - {}", errorCode.getCode(), e.getMessage());
        
        return ResponseEntity.status(errorCode.getStatus())
            .body(errorResponse);
    }
    
    /**
     * IllegalArgumentException 처리
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.of(
            ErrorCode.INVALID_INPUT_VALUE.getCode(),
            e.getMessage(),
            request.getRequestURI()
        );
        
        log.warn("IllegalArgumentException: {}", e.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }
    
    /**
     * @Valid 검증 실패 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        ErrorResponse errorResponse = ErrorResponse.of(
            ErrorCode.INVALID_INPUT_VALUE.getCode(),
            "입력값 검증에 실패했습니다: " + errors.toString(),
            request.getRequestURI()
        );
        
        log.warn("ValidationException: {}", errors);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }
    
    /**
     * 기타 예외 처리 (최종 예외 핸들러)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception e, HttpServletRequest request) {
        
        ErrorResponse errorResponse = ErrorResponse.of(
            ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
            ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
            request.getRequestURI()
        );
        
        log.error("Unexpected error occurred: ", e);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse);
    }
}