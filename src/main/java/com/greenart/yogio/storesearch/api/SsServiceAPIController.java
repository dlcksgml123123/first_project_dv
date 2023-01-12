package com.greenart.yogio.storesearch.api;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.storesearch.entity.SsStoresearchEntity;
import com.greenart.yogio.storesearch.repository.SsMenucateJoinRepository;
import com.greenart.yogio.storesearch.service.SsStoreNameSearchService;


import io.micrometer.common.lang.Nullable;


@RestController
@RequestMapping("/api")

public class SsServiceAPIController {
    @Autowired SsStoreNameSearchService storService;
    
//     //가게 이름으로 검색
//     @GetMapping("/search/{type}")
//    public ResponseEntity<Object> getsearchStoreNameList(@PathVariable String type, @RequestParam @Nullable String keyword, Pageable page){
//     Map<String, Object> resulMap = storService.searchStoreNameList(type, keyword, page);
//     return new ResponseEntity<>(resulMap, HttpStatus.OK);
//    } 
   // 전체 리스트 출력
    @GetMapping("/alllist")
    public ResponseEntity<Object> alllist(Pageable pageable){
    Map<String,Object> map = storService.AllList(pageable);
    return new ResponseEntity<>(map, HttpStatus.OK);
}
    // 메뉴입력 
    @GetMapping("/search/{type}")
    public ResponseEntity <Object> getCateJoinInfo(@PathVariable String type, @RequestParam @Nullable String keyword, Pageable pageable){
    Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    if(type.equals("siName")){
        resulMap.put("store", storService.searchStoreNameList(keyword, pageable));
    }
    else if( type.equals("scName")){
        resulMap.put("store", storService.CateJoinInfo(keyword, pageable));
    }
    return new ResponseEntity<>(resulMap, HttpStatus.OK);
    } 
    

    // @Autowired SsMenucateJoinRepository smcjRepo;
    // @GetMapping("/menu/{type}")
    // public ResponseEntity<Object> getMenuInfo(@PathVariable String type, @RequestParam @Nullable String keyword){
    // // Map<String , Object> resultMap = storService.MenuInfo(type, keyword);
    // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // List<SsMenucateJoinEntity> list = smcjRepo.findAll();
    // // List<String> menuNameList = new ArrayList<String>();

    // for(SsMenucateJoinEntity entity : list) {
    //     if(entity.getMcjMniSeq().getMniName().contains(keyword)) {
    //         list.(entity.getMcjMcSeq().getMcSiSeq().getSiName());
    //         }
    //     }
    //     resultMap.put("list", smcjRepo);
    //     return new ResponseEntity<>(resultMap, HttpStatus.OK);
    //     } 

    // @Autowired SsMenucateJoinRepository smcjRepo;
  
    // @GetMapping("/menu/{type}")
    // public ResponseEntity <Object> getMenuInfo(@PathVariable String type, @RequestParam @Nullable String keyword){
    // Map<String, Object> resulMap = storService.MenuInfo(type, keyword);
    // // Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    // // List<SsMenucateJoinEntity> list = smcjRepo.findAll();   
    
    // resulMap.put("list", smcjRepo);
    // return new ResponseEntity<>(resulMap, HttpStatus.OK);
    // }
            
    } 





    

