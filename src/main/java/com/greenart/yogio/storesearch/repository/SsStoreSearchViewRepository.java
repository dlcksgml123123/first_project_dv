package com.greenart.yogio.storesearch.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.greenart.yogio.storesearch.entity.SsStoreSearchViewEntity;


public interface SsStoreSearchViewRepository extends JpaRepository<SsStoreSearchViewEntity ,Long>{
    Page<SsStoreSearchViewEntity> findBySiNameContains(String siName,Pageable pageable);
     Page<SsStoreSearchViewEntity> findAll(Pageable pageable);
    Page<SsStoreSearchViewEntity> findByScNameContains(String scName,Pageable pageable);

}
