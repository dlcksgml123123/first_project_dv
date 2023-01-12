package com.greenart.yogio.storesearch.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsStoreMenusearchEntity;


@Repository
public interface SsstoreMenusearchRepository extends JpaRepository <SsStoreMenusearchEntity, Long>{
    public List<SsStoreMenusearchEntity> findAll();

    // List<SsStoreMenusearchEntity> findByMcnameContains(String keyword);
  
}
