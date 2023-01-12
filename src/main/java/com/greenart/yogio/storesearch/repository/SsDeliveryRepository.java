package com.greenart.yogio.storesearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsDeliveryEntity;

@Repository
public interface SsDeliveryRepository extends JpaRepository<SsDeliveryEntity,Long>{
    // public List<SsDeliveryEntity> findSsStoresearchEntity();
    //x
}
