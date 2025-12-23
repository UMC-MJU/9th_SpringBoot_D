package com.example.umc9th.domain.member.enums;

public enum TermName { //enum 안에 값이 없을 경우 JPA 오류 발생
    //필수
    SERVICE_AGREEMENT, //서비스 이용 약관
    PRIVACY_POLICY, //개인 정보 처리 방침
    
    //선택
    LOCATION_AGREEMENT, //위치정보 제공
    MARKETING_CONSENT, //마케팅 수신 동의
}
