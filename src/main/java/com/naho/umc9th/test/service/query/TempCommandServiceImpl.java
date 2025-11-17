package com.naho.umc9th.test.service.query;

import com.naho.umc9th.domain.common.apiPayload.exception.TestException;
import com.naho.umc9th.test.exception.TestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag){
        if(flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
