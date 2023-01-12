package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoEntity;

@Repository
public interface SiStoreInfoRepository extends JpaRepository<SiStoreInfoEntity, Long> {
    public List<SiStoreInfoEntity> findBySiSeq(Long siSeq);
       // @Query (value = "select * from store_info where si_img= %:keyword%", nativeQuery = true)
       // public List<SiStoreInfoEntity> getSiImg(@Param("keyword") String keyword);
       // public List<SiStoreInfoEntity> findBySiImg(String siImg);
    public List<SiStoreInfoEntity> findBySiUri(String uri);
}
