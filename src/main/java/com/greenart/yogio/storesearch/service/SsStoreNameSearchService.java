package com.greenart.yogio.storesearch.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.yogio.storesearch.entity.SsCategoryList;
import com.greenart.yogio.storesearch.entity.SsStoreSearchViewEntity;
import com.greenart.yogio.storesearch.entity.SsStoresearchEntity;
import com.greenart.yogio.storesearch.repository.SsDeliveryRepository;

import com.greenart.yogio.storesearch.repository.SsMenucateJoinRepository;
import com.greenart.yogio.storesearch.repository.SsStoreCateJoinRepository;
import com.greenart.yogio.storesearch.repository.SsStoreInfoRepository;
import com.greenart.yogio.storesearch.repository.SsStoreSearchViewRepository;
import com.greenart.yogio.storesearch.repository.SsstoreMenusearchRepository;

import io.micrometer.common.lang.Nullable;

@Service
public class SsStoreNameSearchService {
    @Autowired SsStoreInfoRepository SsNSRepo;
    @Autowired SsstoreMenusearchRepository menuRepo;
    @Autowired SsDeliveryRepository deRepo;
    @Autowired SsstoreMenusearchRepository MenuInfoRefo;
    @Autowired SsMenucateJoinRepository Menucate;
    @Autowired SsStoreCateJoinRepository SstorecateRepo;
    @Autowired SsStoreInfoRepository storeRepo;
    @Autowired SsStoreSearchViewRepository searchRepo;



    // 전체 메뉴조회
    public Map<String,Object>AllList(Pageable pageable){
      Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
      resultMap.put("status", true);
      resultMap.put("message", "전체 메뉴를 조회했습니다.");
      resultMap.put("list", SsNSRepo.findAll(pageable));
      resultMap.put("code", HttpStatus.OK);
      return resultMap;
      }
      // 가게이름으로 조회
   public Map<String, Object> searchStoreNameList(@RequestParam @Nullable String keyword, Pageable pageable) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    Page<SsStoreSearchViewEntity> page = searchRepo.findBySiNameContains(keyword ,pageable);
      resultMap.put("list", page.getContent());
      resultMap.put("message","검색하신 가게 목록입니다.");
      resultMap.put("code",HttpStatus.OK);
      return resultMap;
    }
    // 카테고리별 조회
    public Map<String, Object> CateJoinInfo(@RequestParam @Nullable String keyword, Pageable pageable) {
      Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
      // Page<SsCategoryList> page =  SstorecateRepo.findByScNameContains(keyword, pageable);

     Page<SsStoreSearchViewEntity> page = searchRepo.findByScNameContains(keyword ,pageable);
      resultMap.put("list", page.getContent() );
      return resultMap;
   }
  //  @Autowired SsMenucateJoinRepository smcjRepo;
   
  // public Map<String, Object> MenuInfo(String type, String keyword) {
  //   Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
  //   type.equals("mniName");
  //   resultMap.put("list", smcjRepo);
  //   resultMap.put("code", HttpStatus.OK);
  //   return resultMap;
//   // }
// public Map<String, Object> findByMcnameContains(String type, String keyword) {
//     return null;
// }

  // public Map<String, Object> findByMcNameContains(String type, String keyword) {
  //   return null;
  // }



   
   }

  

  
  
  
   

