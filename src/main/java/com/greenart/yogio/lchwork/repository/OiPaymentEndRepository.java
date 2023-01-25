package com.greenart.yogio.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiPaymentEndEntity;

@Repository
public interface OiPaymentEndRepository extends JpaRepository<OiPaymentEndEntity, Long>{
    OiPaymentEndEntity findByOiOrderNumAndMiSeq(String oiOrderNum, Long miSeq);
}
