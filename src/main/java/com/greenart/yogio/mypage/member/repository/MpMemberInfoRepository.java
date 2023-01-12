package com.greenart.yogio.mypage.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.mypage.member.entity.MpMemberInfoEntity;

@Repository
public interface MpMemberInfoRepository extends JpaRepository<MpMemberInfoEntity, Long>{
  MpMemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);

  MpMemberInfoEntity findByMiSeq(Long miSeq);

  MpMemberInfoEntity findByMiId(String miId);

  
}
