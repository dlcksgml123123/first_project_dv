package com.greenart.yogio.storeInfo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoListEntity;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoListRepository;

@Service
public class SiStoreInfoListService {
    @Autowired SiStoreInfoListRepository siStoreInfoListRepository;
    public Map<String,Object> getStoreList (String keyword, Pageable pageable) {
       Page<SiStoreInfoListEntity> page = siStoreInfoListRepository.findByScNameContains(keyword, pageable) ;
       Map<String,Object> map = new LinkedHashMap<String, Object>();
       map.put("list", page.getContent());
       map.put("total", page.getTotalElements());
       map.put("totalpage", page.getTotalPages());
       return map;
    }
}
