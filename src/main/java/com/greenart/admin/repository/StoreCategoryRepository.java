package com.greenart.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.admin.entity.StoreCategoryEntity;

@Repository
public interface StoreCategoryRepository  extends JpaRepository <StoreCategoryEntity ,Long>{
    
}
