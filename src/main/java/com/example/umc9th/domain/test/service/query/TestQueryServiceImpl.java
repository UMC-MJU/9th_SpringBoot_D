package com.example.umc9th.domain.test.service.query;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.umc9th.domain.test.exception.TestException;
import com.example.umc9th.global.apiPayload.code.ErrorCode;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService{
    @Override
    public void checkFlag(Long flag){
        if(flag == 1L){
            throw new TestException(ErrorCode.TEST001);
        }
    }
}
