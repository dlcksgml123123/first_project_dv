package com.greenart.yogio.admin.dgc.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.member.review.entity.MbReviewEntity;
import com.greenart.yogio.member.review.entity.MbReviewImageEntity;
import com.greenart.yogio.member.review.repository.MbReviewImageRepository;

@Service
public class ADReviewService {
  @Autowired MbReviewImageRepository r_repo;
  public Map<String,Object> getReviewList(Pageable pageable, String keyword){
    Page<MbReviewImageEntity> page = r_repo.findByRiSeqContains(keyword, pageable);
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
