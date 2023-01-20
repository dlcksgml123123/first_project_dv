package com.greenart.yogio.storeInfo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoDetailEntity;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoDetailRepository;

@Service
public class SiStoreInfoDetailService {
    @Autowired SiStoreInfoDetailRepository siStoreInfoDetailRepository;
        public Map<String,Object> getStoreDetail (Long siseq, Pageable pageable) {
       Page<SiStoreInfoDetailEntity> page = siStoreInfoDetailRepository.findBySiSeq(siseq, pageable);
       Map<String,Object> map = new LinkedHashMap<String, Object>();
       map.put("status",true);
       map.put("list", page.getContent());
       map.put("total", page.getTotalElements());
       map.put("totalpage", page.getTotalPages());
       return map;
    }
}
