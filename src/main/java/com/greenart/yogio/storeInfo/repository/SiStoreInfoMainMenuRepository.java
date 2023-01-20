package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoMainMenuEntity;

@Repository
public interface SiStoreInfoMainMenuRepository  extends JpaRepository<SiStoreInfoMainMenuEntity,Long>{
    public List<SiStoreInfoMainMenuEntity> findBySiSeq(Long siSeq);
    
}
