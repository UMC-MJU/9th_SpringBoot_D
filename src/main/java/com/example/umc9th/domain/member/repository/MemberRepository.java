package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.name = :name")
    List<Member> findMemberByName(@Param("name") String name);

    Optional<Member> findByEmail(String email);

    //Member findById(@Param("id") long id); // 기본 지급
}
