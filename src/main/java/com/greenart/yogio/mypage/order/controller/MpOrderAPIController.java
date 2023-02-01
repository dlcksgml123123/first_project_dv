package com.greenart.yogio.mypage.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.admin.raeeun.service.OdOrderService;
import com.greenart.yogio.admin.raeeun.vo.OrderAddVO;
import com.greenart.yogio.mypage.order.service.MpMemberOrderService;

@RestController
@RequestMapping("/order")
public class MpOrderAPIController {
  @Autowired OdOrderService order;
  @Autowired MpMemberOrderService mOrder;

  // 메뉴 추가
  @PostMapping("/add/menu")
  public ResponseEntity<Object> postMenuAdd(@RequestBody OrderAddVO data) {
    Map<String, Object> map = order.addMenu(data);
      return new ResponseEntity<>(map, HttpStatus.OK);
    }
  
  // 옵션 추가
  @PostMapping("/add/option")
  public ResponseEntity<Object> postOptionAdd(@RequestBody OrderAddVO data) {
    Map<String, Object> map = order.addOption(data);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 주문완료 -> 배달완료로 상태 변경
  @GetMapping("/update/status2")
  public ResponseEntity<Object> getOrderUpdateStatus2(@RequestParam String orderNum) {
    Map<String, Object> map = mOrder.updateOrderStatus2(orderNum);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 장바구니 -> 주문완료 상태 변경
  @GetMapping("/update/status1")
  public ResponseEntity<Object> getOrderUpdateStatus1(@RequestParam Long miSeq) {
    Map<String, Object> map = mOrder.updateOrderStatus1(miSeq);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
