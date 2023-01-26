package com.greenart.yogio.storesearch.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.greenart.yogio.storesearch.entity.SsStoreSearchViewEntity;
import com.greenart.yogio.storesearch.repository.SsStoreSearchViewRepository;
import io.micrometer.common.lang.Nullable;


@Service
public class SsStoreNameSearchService {
      @Autowired SsStoreSearchViewRepository searchRepo;
    // 전체 메뉴조회
    public Map<String,Object>AllList(Pageable pageable){
      Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
      resultMap.put("status", true);
      resultMap.put("message", "전체 메뉴를 조회했습니다.");
      resultMap.put("list", searchRepo.findAll());
      resultMap.put("code", HttpStatus.OK);
      return resultMap;
      } 
      // 가게이름으로 조회
   public Map<String, Object> searchStoreNameList(@RequestParam @Nullable String keyword, Pageable pageable) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    Page<SsStoreSearchViewEntity> page = searchRepo.findBySiNameContains(keyword ,pageable);
      if(page.isEmpty()){
        resultMap.put("status", false);
        resultMap.put("message", "입력하신 가게가 없습니다.");
      }else{
      resultMap.put("list", page.getContent());
      resultMap.put("message","검색하신 가게 목록입니다.");
      resultMap.put("code",HttpStatus.OK);
    }
    return resultMap;
  }
  // 카테고리별 조회
  public  Map<String, Object> CateJoinInfo(@RequestParam @Nullable String keyword, Pageable pageable) {
  Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  Page<SsStoreSearchViewEntity> page = searchRepo.findByScNameContains(keyword ,pageable);
  if(page.isEmpty()){
  resultMap.put("status", false);
  resultMap.put("message","검색하신 카테고리에 해당하는 가게가 없습니다.");
}
else {
  resultMap.put("status", true);
  resultMap.put("list", page.getContent());
  resultMap.put("message","검색하신 카테고리 목록입니다.");
  resultMap.put("code",HttpStatus.OK);
  }
      return resultMap;
   }

}


  
  
   

