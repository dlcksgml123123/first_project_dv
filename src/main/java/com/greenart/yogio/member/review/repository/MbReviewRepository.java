package com.greenart.yogio.member.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.member.review.entity.MbReviewEntity;

@Repository
public interface MbReviewRepository extends JpaRepository<MbReviewEntity, Long>{
    public MbReviewEntity findByReOiSeq(Long reOiSeq);
    public Integer countByReOiSeq(Long reOiSeq); 
    
}
