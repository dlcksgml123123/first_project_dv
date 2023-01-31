package com.greenart.yogio.admin.dgc.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.dgc.repository.ADReviewRepository;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.repository.MbMemberInfoRepository;
import com.greenart.yogio.member.review.entity.MbReviewEntity;
import com.greenart.yogio.member.review.entity.MbReviewImageEntity;
import com.greenart.yogio.member.review.repository.MbReviewImageRepository;
import com.greenart.yogio.mypage.review.entity.MpMypageReviewViewEntity;

@Service
public class ADReviewService {
  @Autowired ADReviewRepository r_repo;
  // @Autowired MbMemberInfoRepository m_repo;
  public Map<String,Object> getReviewList(Pageable pageable, String keyword){
    Page<MpMypageReviewViewEntity> page = r_repo.findAll(pageable);
    // MbMemberInfoEntity member = m_repo.findByOiSeq(data.getOiSeq());
    // Page<MpMypageReviewViewEntity> page = r_repo.findByReSeq(keyword, pageable);
    Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("status", true);
    resultMap.put("list", page.getContent());
    resultMap.put("total", page.getTotalElements());
    resultMap.put("totalpage", page.getTotalPages());
    resultMap.put("currentPage", page.getNumber());
    resultMap.put("reviewList", page);
    return resultMap;
  }
}
