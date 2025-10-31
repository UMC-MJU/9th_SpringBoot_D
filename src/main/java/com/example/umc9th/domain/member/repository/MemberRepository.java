package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 조회 (삭제되지 않은)
    Optional<Member> findByEmailAndDeletedAtIsNull(String email);

    // 닉네임으로 회원 조회 (삭제되지 않은)
    Optional<Member> findByNicknameAndDeletedAtIsNull(String nickname);
}
