package com.example.umc9th.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {
    
    private String nickName;
    private String email;  
    private String phone;
    private Boolean phoneVerified;
    private Integer point;
    private Long reviewCount;
    private Long inquiryCount;
    private Boolean eventAlerts;
    private Boolean reviewReplyAlerts;
    private Boolean inquiryReplyAlerts;
}
