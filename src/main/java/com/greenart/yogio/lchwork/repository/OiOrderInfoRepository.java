package com.greenart.yogio.lchwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiOrderInfoEntity;

@Repository
public interface OiOrderInfoRepository extends JpaRepository<OiOrderInfoEntity, Long>{
    List<OiOrderInfoEntity> findByOiMiseq(Long oiMiseq);
}
