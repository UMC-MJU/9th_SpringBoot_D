package com.example.umc9th.repository.member.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.umc9th.domain.member.board.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
    //리뷰 작성 중복 체크용(@Query 어노테이션 사용)
    @Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.member.id = :memberId AND r.store.id = :storeId")
    boolean existsByMemberIdAndStoreId(@Param("memberId") Long memberId, @Param("storeId") Long storeId);

    //작성한 리뷰 개수 조회(메서드 생성 방식)
    long countByMemberId(Long memberId);
}
