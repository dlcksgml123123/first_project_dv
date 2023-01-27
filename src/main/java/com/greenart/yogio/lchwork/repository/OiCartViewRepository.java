package com.greenart.yogio.lchwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiCartViewEntity;

@Repository
public interface OiCartViewRepository extends JpaRepository<OiCartViewEntity, Long>{
    List<OiCartViewEntity> findByOiStatusAndMiSeq(Integer oiStatus, Long miSeq);
}
