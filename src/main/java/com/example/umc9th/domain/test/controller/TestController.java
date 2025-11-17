package com.example.umc9th.domain.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.example.umc9th.domain.test.dto.res.TestResDTO;
import com.example.umc9th.domain.test.converter.TestConverter;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.umc9th.domain.test.service.query.TestQueryService;
@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<TestResDTO.Testing>> test(){
        return ResponseEntity.ok(ApiResponse.onSuccess(
            SuccessCode.TEST200,
            TestConverter.toTestingDTO("THis is Test!")
        ));
    }

    //예외 상황
    @GetMapping("/exception")
    public ResponseEntity<ApiResponse<TestResDTO.Exception>> exception(
        @RequestParam(required = false, defaultValue = "0") Long flag
    ){
        testQueryService.checkFlag(flag);

        return ResponseEntity.ok(ApiResponse.onSuccess(
            SuccessCode.TEST200,
            TestConverter.toExceptionDTO("This is Test!")
        ));
    }
}
