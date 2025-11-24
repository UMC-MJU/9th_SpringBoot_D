package com.naho.umc9th.domain.mission.repository;

import com.naho.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
