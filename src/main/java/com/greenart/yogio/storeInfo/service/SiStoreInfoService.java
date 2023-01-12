package com.greenart.yogio.storeInfo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoEntity;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoRepository;

@Service
public class SiStoreInfoService {
    @Autowired SiStoreInfoRepository sRepo;
    public Map<String,Object> addStoreInfo (SiStoreInfoEntity data) {
         Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
         sRepo.save(data);
         resultMap.put("status", true);
         resultMap.put("message", "가게 정보가 등록되었습니다.");
         resultMap.put("code",HttpStatus.OK);
         return resultMap;
    }

    public String getFilenameByUri(String uri) {
    List<SiStoreInfoEntity> data = sRepo.findBySiUri(uri);
    return data.get(0).getSiFileName();
  }
 
}
