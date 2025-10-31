package com.example.umc9th.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private LocalDateTime timestamp;
    private String code;
    private String message;
    private String path;
    
    public static ErrorResponse of(String code, String message, String path) {
        return new ErrorResponse(
            LocalDateTime.now(),
            code,
            message,
            path
        );
    }
}
