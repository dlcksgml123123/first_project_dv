package com.greenart.yogio.storeInfo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoMenuEntity;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoMenuRepository;

@Service
public class SIStoreInfoMenuService {
     @Autowired SiStoreInfoMenuRepository siStoreInfoMenuRepository;
    public Map<String,Object> getStoreMenu (Long siseq, Pageable pageable) {
       Page<SiStoreInfoMenuEntity> page = siStoreInfoMenuRepository.findBySiSeq(siseq, pageable);
       Map<String,Object> map = new LinkedHashMap<String, Object>();
       map.put("status",true);
       map.put("list", page.getContent());
       map.put("total", page.getTotalElements());
       map.put("totalpage", page.getTotalPages());
       return map;
    }
    
}
