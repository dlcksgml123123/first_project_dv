package com.greenart.yogio.storesearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsCategoryList;

@Repository
public interface SsCategoryRepository extends JpaRepository<SsCategoryList, Long>{
    // List<SsCategoryList> findAll();
// x
    
}
