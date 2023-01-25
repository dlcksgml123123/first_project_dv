package com.greenart.yogio.storesearch.api;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.greenart.yogio.storesearch.service.SsStoreNameSearchService;
import io.micrometer.common.lang.Nullable;


@RestController
@RequestMapping("/api")

public class SsServiceAPIController {
    @Autowired SsStoreNameSearchService storService;
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
    if(type.equals("siName")){ // 가게이름
        resulMap.put("store", storService.searchStoreNameList(keyword, pageable));
    }
    else if(type.equals("scName")){ // 카테고리
        resulMap.put("store", storService.CateJoinInfo(keyword, pageable));
    }
    return new ResponseEntity<>(resulMap, HttpStatus.OK);
    }
    
    // 정렬기준에 따른 리스트 출력 
    @GetMapping("/min/{type}") // 최소주문금액 
    public ResponseEntity <Object> getSortInfo1(@PathVariable String type, @RequestParam @Nullable String keyword, @PageableDefault(sort = "siMinOrderPrice", direction =  Direction.ASC) Pageable pageable ){
    Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    if(type.equals("siName")){ // 가게이름
        resulMap.put("store", storService.searchStoreNameList(keyword, pageable));
    }
    else if(type.equals("scName")){ // 카테고리
        resulMap.put("store", storService.CateJoinInfo(keyword, pageable));
    } 
    return new ResponseEntity<>(resulMap, HttpStatus.OK);
}
     @GetMapping("/average/{type}") // 평점 평균
    public ResponseEntity <Object> getSortInfo2(@PathVariable String type, @RequestParam @Nullable String keyword, @PageableDefault(sort = "average", direction =  Direction.DESC) Pageable pageable ){
    Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    if(type.equals("siName")){ // 가게이름
        resulMap.put("store", storService.searchStoreNameList(keyword, pageable));
    }
    else if(type.equals("scName")){ // 카테고리
        resulMap.put("store", storService.CateJoinInfo(keyword, pageable));
    } 
    return new ResponseEntity<>(resulMap, HttpStatus.OK);
}

    @GetMapping("/reviewcnt/{type}") // 리뷰 수
    public ResponseEntity <Object> getSortInfo3(@PathVariable String type, @RequestParam @Nullable String keyword, @PageableDefault(sort = "ownerReviewCnt", direction =  Direction.DESC) Pageable pageable ){
    Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    if(type.equals("siName")){ // 가게이름
        resulMap.put("store", storService.searchStoreNameList(keyword, pageable));
    }
    else if(type.equals("scName")){ // 카테고리
        resulMap.put("store", storService.CateJoinInfo(keyword, pageable));
    } 
    return new ResponseEntity<>(resulMap, HttpStatus.OK);
}
    @GetMapping("/ditime/{type}") // 배달시간
    public ResponseEntity <Object> getSortInfo4(@PathVariable String type, @RequestParam @Nullable String keyword, @PageableDefault(sort = "diTime", direction =  Direction.ASC) Pageable pageable ){
    Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    if(type.equals("siName")){ // 가게이름
        resulMap.put("store", storService.searchStoreNameList(keyword, pageable));
    }
    else if(type.equals("scName")){ // 카테고리
        resulMap.put("store", storService.CateJoinInfo(keyword, pageable));
    } 
    return new ResponseEntity<>(resulMap, HttpStatus.OK);
}      
}
