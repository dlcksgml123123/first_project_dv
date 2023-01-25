package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoPlusMenuEntity;

@Repository
public interface SiStoreInfoPlusMenuRepository extends JpaRepository<SiStoreInfoPlusMenuEntity,Long>{
   public List<SiStoreInfoPlusMenuEntity> findByMniSeq (Long mniSeq);
   
    
}
