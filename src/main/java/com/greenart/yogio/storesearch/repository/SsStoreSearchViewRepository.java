package com.greenart.yogio.storesearch.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsStoreSearchViewEntity;

@Repository
public interface SsStoreSearchViewRepository extends JpaRepository<SsStoreSearchViewEntity ,Long>{
    Page<SsStoreSearchViewEntity> findBySiNameContains(String siName,Pageable pageable);

    Page<SsStoreSearchViewEntity> findByScNameContains(String scName,Pageable pageable);

    // Page<SsStoreSearchViewEntity> findByMniNameContains(String mniName,Pageable pageable);
    
}
