package com.greenart.yogio.storesearch.repository;



import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsStoresearchEntity;

@Repository
public interface SsStoreInfoRepository extends JpaRepository<SsStoresearchEntity, Long>{
    public List<SsStoresearchEntity> findAll();
   
    Page<SsStoresearchEntity> findBySiNameContains(String keyword, Pageable pageable);

    Page<SsStoresearchEntity> findBySiSeq(Long siSeq, Pageable pageable);
  
    // @Query(value = "SELECT m FROM SsStoresearchEntity m WHERE m.siName LIKE '%:keyword%'")
    // public Page<SsStoresearchEntity> findSiname(@Param("keyword") String keyword, Pageable page);

    

  
}

    

    

