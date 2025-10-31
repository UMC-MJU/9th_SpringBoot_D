package com.naho.umc9th.domain.mission.service;

import com.naho.umc9th.domain.common.enums.District;
import com.naho.umc9th.domain.mission.dto.HomeMissionDto;
import com.naho.umc9th.domain.mission.enums.MissionStatus;
import com.naho.umc9th.domain.mission.repository.HomeMissionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeMissionService {

    private final HomeMissionRepository homeMissionRepository;

    private static final Map<String, District> districtMap = new HashMap<>();

    @PostConstruct
    public void initDistrictMap(){
        districtMap.put("강남구", District.GANGNAM);
        districtMap.put("강동구", District.GANGDONG);
        districtMap.put("강북구", District.GANGBUK);
        districtMap.put("강서구", District.GANGSEO);
        districtMap.put("관악구", District.GWANAK);
        districtMap.put("광진구", District.GWANGJIN);
        districtMap.put("구로구", District.GURO);
        districtMap.put("금천구", District.GEUMCHEON);
        districtMap.put("노원구", District.NOWON);
        districtMap.put("도봉구", District.DOBONG);
        districtMap.put("동대문구", District.DONGDAEMUN);
        districtMap.put("동작구", District.DONGJAK);
        districtMap.put("마포구", District.MAPO);
        districtMap.put("서대문구", District.SEODAEMUN);
        districtMap.put("서초구", District.SEOCHO);
        districtMap.put("성동구", District.SEONGDONG);
        districtMap.put("성북구", District.SEONGBUK);
        districtMap.put("송파구", District.SONGPA);
        districtMap.put("양천구", District.YANGCHEON);
        districtMap.put("영등포구", District.YEONGDEUNGPO);
        districtMap.put("용산구", District.YONGSAN);
        districtMap.put("은평구", District.EUNPYEONG);
        districtMap.put("종로구", District.JONGNO);
        districtMap.put("중구", District.JUNGGU);
        districtMap.put("중랑구", District.JUNGNANG);
    }

    private District convertDistrictNameToEnum(String districtName){
        District district = districtMap.get(districtName);
        if(district == null) {
            throw new IllegalArgumentException("유효하지 않은 지역입니다.");
        }
        return district;
    }

    public Page<HomeMissionDto> getHomeMissions(Long memberId, String city, String district, int page) {

        MissionStatus status = MissionStatus.IN_PROGRESS;

        District districtEnum = convertDistrictNameToEnum(district);

        int size = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        //OFFSET 0 (page = 0), OFFSET 10 (page = 1)
        Pageable pageable = PageRequest.of(page, size, sort);

        return homeMissionRepository.findInProgressMissionsByRegion(
                memberId,
                status,
                city,
                districtEnum,
                pageable
        );
    }


}
