package com.example.umc9th.repository.member.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.umc9th.domain.member.board.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    
    // 회원의 문의 개수 조회(@Query 어노테이션 사용)
    @Query("SELECT COUNT(i) FROM Inquiry i WHERE i.member.id = :memberId")
    long countByMemberId(@Param("memberId") Long memberId);
}
