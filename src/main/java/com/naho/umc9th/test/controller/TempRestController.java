package com.naho.umc9th.test.controller;

import com.naho.umc9th.domain.common.apiPayload.ApiResponse;
import com.naho.umc9th.test.dto.TestResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {


    //예외 사항
    @GetMapping("/rest-exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ) {
        return null;
    }
}
