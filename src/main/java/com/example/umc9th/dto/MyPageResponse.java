package com.example.umc9th.dto;

public record MyPageResponse(
    String nickName,
    String email,
    String phone,
    Boolean phoneVerified,
    Integer point,
    Long reviewCount,
    Long inquiryCount,
    Boolean eventAlerts,
    Boolean reviewReplyAlerts,
    Boolean inquiryReplyAlerts
) {}
