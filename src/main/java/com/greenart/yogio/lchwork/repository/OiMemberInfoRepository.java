package com.greenart.yogio.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;

@Repository
public interface OiMemberInfoRepository extends JpaRepository<MbMemberInfoEntity, Long> {
    public MbMemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    public Integer countByMiId(String miId);
}
