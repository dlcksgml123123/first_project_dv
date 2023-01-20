package com.greenart.yogio.storeInfo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoDetailEntity;

@Repository
public interface SiStoreInfoDetailRepository extends JpaRepository <SiStoreInfoDetailEntity , Long>{
  @Query (value = "select * from store_info_detail order by si_seq", nativeQuery = true)
  Page<SiStoreInfoDetailEntity> getStoreDetail(Pageable page);

  public Page<SiStoreInfoDetailEntity> findBySiSeq(Long siSeq, Pageable pageable);

}
