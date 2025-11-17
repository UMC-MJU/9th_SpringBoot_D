package com.naho.umc9th.domain.member.repository;

import com.naho.umc9th.domain.member.dto.MemberPageDto;
import com.naho.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // JpaRepository<관리할 엔티티, 엔티티의 PK 타입

    @Query("SELECT new com.naho.umc9th.domain.member.dto.MemberPageDto(m.email, m.nickname, m.phoneNumber, m.point)" +
            "FROM Member m WHERE m.id = :memberId")
    Optional<MemberPageDto> findMemberPageById(@Param("memberId") Long memberId);

    // 워크북 따라하기
    List<Member> findByNameAndDeletedAtIsNull(String name);

    @Query("select m from Member m where m.name = :name and m.deletedAt is null")
    List<Member> findActiveMember(@Param("name") String name);
    //:name - 파라미터 연결

}
