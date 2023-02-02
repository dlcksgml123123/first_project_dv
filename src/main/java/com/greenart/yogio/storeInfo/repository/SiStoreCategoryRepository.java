package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.admin.entity.StoreCategoryEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreCategoryEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreInfoEntity;

@Repository
public interface SiStoreCategoryRepository  extends JpaRepository <SiStoreCategoryEntity ,Long>{
    public StoreCategoryEntity findByScNameContains(String scName);
     public List<SiStoreCategoryEntity> findByScImage(String uri);
}
