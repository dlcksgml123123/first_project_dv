package com.greenart.yogio.member.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.member.review.entity.MbReviewEntity;
import com.greenart.yogio.member.review.entity.MbReviewImageEntity;

@Repository
public interface MbReviewImageRepository extends JpaRepository<MbReviewImageEntity, Long>{
  public Page<MbReviewImageEntity> findByRiSeqContains(String keyword, Pageable pageable);
}
