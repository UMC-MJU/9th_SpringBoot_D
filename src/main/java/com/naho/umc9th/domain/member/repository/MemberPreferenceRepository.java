package com.naho.umc9th.domain.member.repository;

import com.naho.umc9th.domain.member.entity.mapping.MemberPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPreferenceRepository extends JpaRepository<MemberPreference, Long> {
}
