package com.greenart.yogio.member.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.yogio.member.review.entity.MbOrderInfoEntity;

public interface MbOrderInfoRepository extends JpaRepository<MbOrderInfoEntity, Long>{
    public MbOrderInfoEntity findByOiSeq(Long oiSeq);
    public MbOrderInfoEntity findByOiMiSeq(Long oiMiSeq);
    public MbOrderInfoEntity findTop1ByOiOrderNumContains (String orderNum);
}
