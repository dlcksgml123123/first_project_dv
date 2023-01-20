package com.greenart.yogio.storeInfo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoMenuEntity;

public interface SiStoreInfoMenuRepository extends JpaRepository <SiStoreInfoMenuEntity,Long> {
    public Page<SiStoreInfoMenuEntity> findBySiSeq(Long siSeq, Pageable pageable);
}
