package com.example.umc9th.dto.auth;

import java.util.List;

public record SignUpResponse(
        Long memberId,
        String nickName,
        String addressName,
        List<String> preferredCategories
) {
}
