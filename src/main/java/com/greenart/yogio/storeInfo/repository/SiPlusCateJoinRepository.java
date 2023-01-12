package com.greenart.yogio.storeInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiPlusCateJoinEntity;

@Repository
public interface SiPlusCateJoinRepository  extends JpaRepository<SiPlusCateJoinEntity, Long> {
    
}
