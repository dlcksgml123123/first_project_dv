package com.greenart.yogio.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.member.entity.MemberInfoEntity;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity, Long>{
    public Integer countBymiId(String miId);
}
