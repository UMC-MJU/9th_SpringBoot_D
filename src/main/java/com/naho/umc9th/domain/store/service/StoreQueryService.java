package com.naho.umc9th.domain.store.service;

import com.naho.umc9th.domain.store.dto.StoreResDTO;

public interface StoreQueryService {
    StoreResDTO.MissionListDTO getMissionList(Long storeId, Integer page);
}
