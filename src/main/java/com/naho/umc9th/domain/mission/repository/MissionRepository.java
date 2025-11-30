package com.naho.umc9th.domain.mission.repository;

import com.naho.umc9th.domain.mission.entity.Mission;
import com.naho.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 가게로 미션 찾기 (페이징 처리)
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}
