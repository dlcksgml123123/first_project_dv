package com.greenart.yogio.storeInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.admin.entity.StoreCateJoinEntity;


@Repository
public interface SiStoreCateJoinRepostiory extends JpaRepository <StoreCateJoinEntity, Long> {

    // void save(StoreCateJoinEntity sc);
    
}
