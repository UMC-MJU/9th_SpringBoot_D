package com.naho.umc9th.test.converter;

import com.naho.umc9th.test.dto.TestResDTO;

public class TestConverter {

    // 객체 -> DTO
    public static TestResDTO.Testing toTestingDTO(
            String testing
    ) {
        return TestResDTO.Testing.builder()
                .testing(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResDTO.Exception toExceptionDTO(
            String testing
    ){
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
