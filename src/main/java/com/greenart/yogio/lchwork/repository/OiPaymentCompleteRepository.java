package com.greenart.yogio.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiPaymentCompleteEntity;

@Repository
public interface OiPaymentCompleteRepository extends JpaRepository<OiPaymentCompleteEntity, Long>{
    OiPaymentCompleteEntity findByMiSeqAndSiSeq(Long miSeq, Long siSeq);
}
