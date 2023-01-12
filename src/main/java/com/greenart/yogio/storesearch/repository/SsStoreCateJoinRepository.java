package com.greenart.yogio.storesearch.repository;





import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
 
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsCategoryList;
import com.greenart.yogio.storesearch.entity.SsStoreCateJoinEntoty;

@Repository
public interface SsStoreCateJoinRepository extends JpaRepository<SsCategoryList,Long>{
    Page<SsCategoryList> findByScNameContains(String keyword, Pageable pageable);

   
}
