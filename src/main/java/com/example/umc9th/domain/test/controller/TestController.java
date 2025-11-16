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
@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<TestResDTO.Testing>> test(){
        return ResponseEntity.ok(ApiResponse.onSuccess(
            SuccessCode.SUCCESS,
            TestConverter.toTestingDTO("THis is Test!")
        ));
    }
}
