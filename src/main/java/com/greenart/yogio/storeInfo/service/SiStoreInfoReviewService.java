package com.greenart.yogio.storeInfo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoReviewEntity;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoReviewRepository;

@Service
public class SiStoreInfoReviewService {
    @Autowired SiStoreInfoReviewRepository siStoreInfoReviewRepository;
    public Map<String,Object> getStoreReview (Long siseq, Pageable pageable) {
       Page<SiStoreInfoReviewEntity> page = siStoreInfoReviewRepository.findBySiSeq(siseq, pageable);
       Map<String,Object> map = new LinkedHashMap<String, Object>();
       map.put("status",true);
       map.put("list", page.getContent());
       map.put("total", page.getTotalElements());
       map.put("totalpage", page.getTotalPages());
       return map;
    }
}
