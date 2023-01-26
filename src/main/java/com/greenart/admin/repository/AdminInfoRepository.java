package com.greenart.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.admin.entity.AdminInfoEntity;

@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfoEntity,Long> {
    public AdminInfoEntity findByAiIdAndAiPwd(String aiId, String aiPwd);
    public Integer countByAiId(String aiId);
}
