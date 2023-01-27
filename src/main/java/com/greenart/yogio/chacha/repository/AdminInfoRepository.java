package com.greenart.yogio.chacha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.chacha.entity.AdminInfoEntity;

@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfoEntity,Long> {
    public AdminInfoEntity findByAiIdAndAiPwd(String aiId, String aiPwd);
    public Integer countByAiId(String aiId);
}
