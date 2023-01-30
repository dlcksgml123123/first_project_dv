package com.greenart.yogio.mypage.store.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.mypage.order.service.MpMemberOrderService;
import com.greenart.yogio.mypage.review.service.MpReviewService;
import com.greenart.yogio.mypage.store.entity.MpStoreCategoryEntity;
import com.greenart.yogio.mypage.store.entity.MpStoreInfoEntity;
import com.greenart.yogio.mypage.store.repository.MpStoreCategoryRepository;
import com.greenart.yogio.mypage.store.service.MpStoreService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/mypage")
public class MpStoreController {
  @Autowired MpStoreService sService;
  @Autowired MpReviewService rService;
  @Autowired MpMemberOrderService order;
  @Autowired MpStoreCategoryRepository storeCateRepo;


  // 찜한 가게 리스트 출력
  @GetMapping("/store")
  public ResponseEntity<Object> getlikedStore(HttpSession session,
  @PageableDefault(size=8) Pageable pageable) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("store", sService.showLikedStore(session, pageable));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 주문 내역 출력
  @GetMapping("/order")
  public ResponseEntity<Object> getOrderList(HttpSession session,Pageable pageable) {
    Map<String, Object> map = order.showOrderList(session, pageable);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 내가쓴 리뷰 출력
  @GetMapping("/review")
  public ResponseEntity<Object> getReviewList(HttpSession session, MbMemberInfoEntity memberInfo,
      Pageable pageable) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    memberInfo = (MbMemberInfoEntity) session.getAttribute("loginUser");
    map.put("review", rService.showReviewList(memberInfo, session, pageable));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 리뷰 삭제
  @GetMapping("/deleteReview")
   public ResponseEntity<Object> deleteReview ( HttpSession session, MbMemberInfoEntity memberInfo, @RequestParam Long reSeq) {
    Map<String, Object> map = rService.deleteReview(session, memberInfo, reSeq); 
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 리뷰 수 출력
  @GetMapping("/reviewCnt")
  public ResponseEntity<Object> showReviewCnt(MpStoreInfoEntity store) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("reviewCnt", rService.showReviewCnt(store.getSiSeq()));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 가게 카테고리 출력
  @GetMapping("/storecate")
  public ResponseEntity<Object> getStoreCate(MpStoreCategoryEntity storeCate, Pageable pageable) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("status", true);
    map.put("storeCate", sService.showStoreCate(pageable));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

  // 간단한 주문 내역 출력
  @GetMapping("/briefOrder")
  public ResponseEntity<Object> getBriefOrderList(HttpSession session,
      Pageable pageable) {
    Map<String, Object> map = order.showBriefOrderList(session, pageable);

    // map.put("order", order.showBriefOrderList(memberInfo, session, pageable));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  @GetMapping("/wishList")
  public ResponseEntity<Object> getWishList(HttpSession session) {
    Map<String, Object> map = order.showWishList(session);
    // map.put("order", order.showBriefOrderList(memberInfo, session, pageable));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

}
  