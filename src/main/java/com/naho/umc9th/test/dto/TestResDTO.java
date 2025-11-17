package com.naho.umc9th.test.dto;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testing;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}
