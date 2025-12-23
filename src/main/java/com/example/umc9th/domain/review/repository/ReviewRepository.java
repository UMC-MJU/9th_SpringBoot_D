package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
    
    // 점포 리뷰 조회
    List<Review> findAllByStore_Id(Long id);
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
    // 작성한 리뷰(내가 작성한 리뷰 페이지)
    List<Review> findByMemberAndStoreAndDeletedAtIsNull(Member member, Store store);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}
