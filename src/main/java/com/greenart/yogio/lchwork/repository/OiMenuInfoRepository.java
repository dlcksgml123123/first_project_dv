package com.greenart.yogio.lchwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiMenuInfoEntity;

@Repository
public interface OiMenuInfoRepository extends JpaRepository<OiMenuInfoEntity, Long>{
    public List<OiMenuInfoEntity> findByMniImg(String uri);
    public Integer countByMniName(String mniName);
    public OiMenuInfoEntity findByMniName(String mniName);
}   
