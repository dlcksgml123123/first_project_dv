package com.greenart.yogio.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;

@Repository
public interface OiMemberInfoRepository extends JpaRepository<OiMemberInfoEntity, Long> {
    public OiMemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    public Integer countByMiId(String miId);
}
