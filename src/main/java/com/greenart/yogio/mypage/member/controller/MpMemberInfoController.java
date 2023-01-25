package com.greenart.yogio.mypage.member.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.mypage.member.entity.MpMemberInfoEntity;
import com.greenart.yogio.mypage.member.repository.MpMemberInfoRepository;
import com.greenart.yogio.mypage.member.service.MpMemberService;
import com.greenart.yogio.mypage.member.vo.MpLoginUserVO;
import com.greenart.yogio.mypage.member.vo.MpUpdateMemberVO;
import com.greenart.yogio.mypage.order.service.MpMemberOrderService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MpMemberInfoController {
  @Autowired MpMemberInfoRepository mRepo;
  @Autowired MpMemberService mService;
  @Autowired MpMemberOrderService order;
  
  // 로그인
  @PostMapping("/login")
  public ResponseEntity<Object> memberLogin(@RequestBody MpLoginUserVO data, HttpSession session) {
    Map<String, Object> resultMap = mService.loginMember(data);
    // session에 등록해야 실제 로그인 처리가 됨
    session.setAttribute("loginUser", resultMap.get("loginUser"));
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code") );
  }
  
  // 로그아웃
  @GetMapping("/logout")
  public ResponseEntity<Object> getLogout(HttpSession session) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    // session.invalidate(); // 세션 정보 모두 삭제
    session.setAttribute("loginUser", null); // 로그인 정보만 삭제
    resultMap.put("message", "로그아웃 되었습니다.");
    return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
  }

  // memberList 출력
  @GetMapping("/memberlist")
  public ResponseEntity<Object> updateMember() {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    List<MpMemberInfoEntity> mlist = new ArrayList<MpMemberInfoEntity>();
    mlist = mRepo.findAll();
    map.put("list", mlist);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // member 정보 수정
  @PatchMapping("mypage/update")
  public ResponseEntity<Object> patchMemberUpdate(@RequestBody MpUpdateMemberVO data, HttpSession session) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    try {
      map.put("member", mService.updateMember(data, session));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
  
  // 로그인 된 계정 정보 출력
  @GetMapping("/loginuser")
  public ResponseEntity<Object> getLoginMember(HttpSession session) {
    Map<String, Object> map = mService.showMemberInfo(session);
    return new ResponseEntity<>(map, HttpStatus.OK);
  }

    // 주문번호를 통한 주문 내역 조회
  @GetMapping("/order") 
  public ResponseEntity<Object> getOrderList(@RequestParam String orderNum) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("order", order.showOrder(orderNum));
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
