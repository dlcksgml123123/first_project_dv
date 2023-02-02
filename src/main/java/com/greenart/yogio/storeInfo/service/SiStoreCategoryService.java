package com.greenart.yogio.storeInfo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiStoreCategoryEntity;
import com.greenart.yogio.storeInfo.repository.SiStoreCategoryRepository;

@Service
public class SiStoreCategoryService {
    @Autowired SiStoreCategoryRepository siStoreCategoryRepository;

    public String getFilenameByUri(String uri) {
    List<SiStoreCategoryEntity> data = siStoreCategoryRepository.findByScImage(uri);
    return data.get(0).getScFileName();
  }
      public Map<String,Object> addStoreInfo (SiStoreCategoryEntity data) {
         Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
         siStoreCategoryRepository.save(data);
         resultMap.put("status", true);
         resultMap.put("message", "가게 정보가 등록되었습니다.");
         resultMap.put("code",HttpStatus.OK);
         return resultMap;
    }
 
}
