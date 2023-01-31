package com.greenart.yogio.admin.dgc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.yogio.admin.dgc.entity.ADReviewEntity;
import com.greenart.yogio.mypage.review.entity.MpMypageReviewViewEntity;

public interface ADReviewRepository extends JpaRepository<ADReviewEntity, Long>{
  public Page<ADReviewEntity> findByReSeq(Long reSeq, Pageable pageable);
  public Page<ADReviewEntity> findByMiNicknameContains(String keyword, Pageable pageable);
  // public MbMemberInfoEntity 
}
