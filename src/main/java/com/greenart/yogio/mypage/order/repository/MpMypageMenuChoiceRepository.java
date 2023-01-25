package com.greenart.yogio.mypage.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.mypage.order.entity.MpMypageMenuChoiceEntity;

@Repository
public interface MpMypageMenuChoiceRepository extends JpaRepository<MpMypageMenuChoiceEntity, Long>{
  List<MpMypageMenuChoiceEntity> findByMiSeq(Long miSeq);

  List<MpMypageMenuChoiceEntity> findByOiOrderNum(String oiOrderNum);

  List<MpMypageMenuChoiceEntity> findByOiSeq(Long oiSeq);
}
