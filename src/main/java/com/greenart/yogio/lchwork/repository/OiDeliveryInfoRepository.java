package com.greenart.yogio.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiDeliveryInfoEntity;

@Repository
public interface OiDeliveryInfoRepository extends JpaRepository<OiDeliveryInfoEntity, Long>{
    OiDeliveryInfoEntity findByDiDistanceAndDiDeliveryPriceAndDiTime(Integer diDistance, Integer diDeliveryPrice, String diTime);
    OiDeliveryInfoEntity findByDiSeq(Long diSeq);
}
