package com.greenart.yogio.admin.dgc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.greenart.yogio.mypage.review.entity.MpMypageReviewViewEntity;

public interface ADReviewRepository extends JpaRepository<MpMypageReviewViewEntity, Long>{
  public Page<MpMypageReviewViewEntity> findByReSeq(Long reSeq, Pageable pageable);
  // public MbMemberInfoEntity 
}
