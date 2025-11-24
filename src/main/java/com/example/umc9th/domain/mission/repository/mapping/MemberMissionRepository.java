package com.example.umc9th.domain.mission.repository.mapping;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
