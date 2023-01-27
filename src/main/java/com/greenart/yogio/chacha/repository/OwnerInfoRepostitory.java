package com.greenart.yogio.chacha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.chacha.entity.OwnerInfoEntity;

@Repository
public interface OwnerInfoRepostitory extends JpaRepository<OwnerInfoEntity,Long>{
    public OwnerInfoEntity findByOwiIdAndOwiPwd(String owiId, String owiPwd);
    public Integer countByOwiId(String owiId);
    
}
