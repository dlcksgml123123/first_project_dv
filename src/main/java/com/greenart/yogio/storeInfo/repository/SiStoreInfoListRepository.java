package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoListEntity;

@Repository
public interface SiStoreInfoListRepository extends JpaRepository <SiStoreInfoListEntity , Long> {
@Query (value = "select * from store_info_list where sc_name like %:keyword% order by si_seq", nativeQuery = true)
 public List<SiStoreInfoListEntity> getStoreList(@Param("keyword") String keyword, Pageable page);

 public Page<SiStoreInfoListEntity> findByScNameContains(String scName, Pageable pageable);

 @Query(value = "select ceil(count(b)/5) from SiStoreInfoListEntity b where b.scName like %:keyword%") 
  Integer getStorePageCount(@Param("keyword") String keyword);
}
