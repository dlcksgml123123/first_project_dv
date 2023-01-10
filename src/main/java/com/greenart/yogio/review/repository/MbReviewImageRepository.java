package com.greenart.yogio.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.review.entity.MbReviewEntity;
import com.greenart.yogio.review.entity.MbReviewImageEntity;

@Repository
public interface MbReviewImageRepository extends JpaRepository<MbReviewImageEntity, Long>{
    
}
