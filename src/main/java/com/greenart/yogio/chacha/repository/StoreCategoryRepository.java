package com.greenart.yogio.chacha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.chacha.entity.StoreCategoryEntity;

@Repository
public interface StoreCategoryRepository  extends JpaRepository <StoreCategoryEntity ,Long>{
    
}
