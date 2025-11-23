package com.example.umc9th.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.umc9th.domain.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    boolean existsByNickName(String nickName);
}
