package com.greenart.yogio.member.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PutMapping("/join") //회원가입
    public ResponseEntity<Object> memberJoin(@RequestBody MbMemberVO data) {
      Map<String, Object> resultMap = mService.addMember(data);
      return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
    @PostMapping("/login") //로그인
    public ResponseEntity<Object> memberLogin(@RequestBody MbLoginVO data/* , HttpSession session*/) {
    Map<String, Object> resultMap = mService.loginMember(data);
    // session.setAttribute("loginUser", resultMap.get("loginUser"));
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }

  @PostMapping("/dupchkId") //아이디중복체크
  public ResponseEntity<Object> memberChkId(@RequestBody MbMemberVO data) {
    Map<String, Object> resultMap = mService.dupchkMemberId(data);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }

  @PostMapping("/searchId") //아이디찾기
  public ResponseEntity<Object> memberSearchId(@RequestBody MbMemberVO data) {
    Map<String, Object> resultMap = mService.searchMemberId(data);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }

  @PostMapping("/searchPwd") //비밀번호찾기
  public ResponseEntity<Object> memberSearchPwd(@RequestBody MbMemberVO data) throws Exception{
    Map<String, Object> resultMap = mService.searchMemberPwd(data);
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }

  @DeleteMapping("/deleteMember") //회원탈퇴
  public ResponseEntity<Object> memberDelete(@RequestParam Long miSeq) throws Exception{
    Map <String ,Object> resultMap = mService.deleteMember(miSeq);
   // session.setAttribute("loginUser", resultMap.get("loginUser"));
    return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
  }
}
