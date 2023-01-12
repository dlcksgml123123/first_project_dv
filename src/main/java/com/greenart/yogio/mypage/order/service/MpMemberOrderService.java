package com.greenart.yogio.mypage.order.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.mypage.member.entity.MpMemberInfoEntity;
import com.greenart.yogio.mypage.order.repository.MpOrderInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class MpMemberOrderService {
  @Autowired
  MpOrderInfoRepository oRepo;
  
  public Map<String, Object> showOrderList(MpMemberInfoEntity memberInfo, HttpSession session) {
    Map<String, Object> map = new LinkedHashMap<>();
    MpMemberInfoEntity member = (MpMemberInfoEntity) session.getAttribute("loginUser");
    if (member == null) {
      map.put("status", false);
      map.put("message", "로그인 후 이용하실 수 있습니다.");
    }
    else {
      map.put("status", true);
      map.put("menu", oRepo.findByMember(member).getMenu());
      map.put("orderAmount", oRepo.findByMember(member).getOiMenuAmount());
    }
    return map;
  }
}
