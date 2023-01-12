package com.greenart.yogio.mypage.order.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.mypage.member.entity.MpMemberInfoEntity;
import com.greenart.yogio.mypage.order.entity.MpOrderInfoEntity;
import com.greenart.yogio.mypage.store.entity.MpMenuInfoEntity;

@Repository
public interface MpOrderInfoRepository extends JpaRepository<MpOrderInfoEntity, Long>{
  MpOrderInfoEntity findByMember(MpMemberInfoEntity member);

  List<MpOrderInfoEntity> findAllByMember(MpMemberInfoEntity member);

  MpOrderInfoEntity findByMenu(MpMenuInfoEntity menu);
}
