package com.greenart.yogio.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiStoreInfoEntity;

@Repository
public interface OiStoreInfoRepository extends JpaRepository<OiStoreInfoEntity, Long>{
    OiStoreInfoEntity findBySiName(String siName);
    OiStoreInfoEntity findBySiSeq (Long siSeq);
}
