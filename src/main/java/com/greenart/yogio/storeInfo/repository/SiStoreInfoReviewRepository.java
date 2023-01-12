package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoReviewEntity;

@Repository
public interface SiStoreInfoReviewRepository  extends JpaRepository<SiStoreInfoReviewEntity, Long>{
  @Query (value = "select * from store_info_review order by si_seq", nativeQuery = true)
  Page<SiStoreInfoReviewEntity> getStoreReview(Pageable page);

  public Page<SiStoreInfoReviewEntity> findBySiSeq(Long siSeq, Pageable pageable);
  
    
}
