package com.greenart.yogio.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.review.entity.MbReviewEntity;

@Repository
public interface MbReviewRepository extends JpaRepository<MbReviewEntity, Long>{
    
}
