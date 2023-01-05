package com.greenart.yogio.member.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.service.MbMemberService;
import com.greenart.yogio.member.vo.MbLoginVO;
import com.greenart.yogio.member.vo.MbMemberVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MbMemberAPIController {
    @Autowired MbMemberService mService;
    @PutMapping("/join")
    public ResponseEntity<Object> memberJoin(@RequestBody MbMemberInfoEntity data) {
      Map<String, Object> resultMap = mService.addMember(data);
      return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @PostMapping("/login")
    public ResponseEntity<Object> memberLogin(@RequestBody MbLoginVO data, HttpSession session) {
    Map<String, Object> resultMap = mService.loginMember(data);
    session.setAttribute("loginUser", resultMap.get("loginUser"));
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }

  @PostMapping("/searchId")
  public ResponseEntity<Object> memberSearchId(@RequestBody MbMemberVO data) {
    Map<String, Object> resultMap = mService.searchMemberId(data);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }
}
