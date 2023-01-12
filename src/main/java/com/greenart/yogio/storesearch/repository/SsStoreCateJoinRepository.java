package com.greenart.yogio.storesearch.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
 
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsCategoryList;


@Repository
public interface SsStoreCateJoinRepository extends JpaRepository<SsCategoryList,Long>{
    Page<SsCategoryList> findByScNameContains(String keyword, Pageable pageable);

   
}
