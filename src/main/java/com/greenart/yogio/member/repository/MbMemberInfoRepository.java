package com.greenart.yogio.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;

@Repository
public interface MbMemberInfoRepository extends JpaRepository<MbMemberInfoEntity, Long>{
    public Integer countByMiId(String miId);
    public Integer countByMiEmail(String miEmail);
    public Integer countByMiPhone(String miPhone);
    public MbMemberInfoEntity findByMiId(String miId);
    public MbMemberInfoEntity findTop1ByMiPwd(String miPwd);
    public MbMemberInfoEntity findByMiPhone(String miPhone);
    public MbMemberInfoEntity findTop1ByMiIdAndMiPwd(String miId, String miPwd);
    public MbMemberInfoEntity findByMiIdAndMiPhone(String miId, String miPhone);
}
  