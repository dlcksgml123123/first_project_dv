package com.greenart.yogio.lchwork.Controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

// import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.lchwork.service.OiMemberService;
import com.greenart.yogio.lchwork.vo.OiLoginMemberVO;
import com.greenart.yogio.lchwork.vo.OiLoginVO;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.repository.MbMemberInfoRepository;
import com.greenart.yogio.member.service.MbMemberService;

import jakarta.servlet.http.HttpSession;

// import com.greenart.yogio.member.repository.MemberInfoRepository;

@RestController
@RequestMapping("/member")
public class OiMemberController {
    @Autowired OiMemberService oimService;
    @Autowired MbMemberService mbmService;
    @Autowired MbMemberInfoRepository mbmRepo;
    // @PutMapping("/join1")
    // public ResponseEntity<Object> memberJoin(@RequestBody MbMemberInfoEntity data) {
    //     Map<String, Object> resultMap = oimService.addMember(data);
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    // }
    // @PostMapping("/login2")
    // public ResponseEntity<Object> memberLogin(@RequestBody OiLoginVO data, HttpSession session) throws Exception {
    //     Map<String, Object> resultMap = oimService.LoginMember(data);
    //     session.setAttribute("loginUser", resultMap.get("loginUser"));
    //     return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));

    // }
    // @GetMapping("/logout")
    // public ResponseEntity<Object> memberLogout(HttpSession session) {
    //     session.invalidate();
    //     return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    // }
    // @GetMapping("/logout")
    // public HashMap<String, Object> memberLogout(HttpSession session) {
    //     HashMap<String, Object> hashMap = new HashMap<String, Object>();
    //     session.invalidate();
    //     hashMap.put("message", "로그아웃 성공");
    //     return hashMap;
    // }

    // @GetMapping("/phoneaddress1")
    // public Map<String, Object> memberInfo(HttpSession session) {
    //     Map<String, Object> map = new LinkedHashMap<String, Object>();
    //     MbMemberInfoEntity loginMember = (MbMemberInfoEntity)session.getAttribute("loginUser");
    //     if(loginMember == null) {
    //         map.put("status", false);
    //         map.put("message", "로그인 후 이용 가능 서비스");
    //     }
    //     else {
    //         OiLoginMemberVO memberDetail = new OiLoginMemberVO(loginMember);
    //         map.put("status", true);
    //         map.put("memberInfo", memberDetail);
    //     }
    //     return map;
    // }

    @GetMapping("/phoneaddress")
    public Map<String, Object> phoneAddressInfo(/*HttpSession session*/Long miSeq) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MbMemberInfoEntity member = mbmRepo.findByMiSeq(miSeq);
            if(member == null) {
                map.put("status", false);
                map.put("message", "회원 정보를 찾을 수 없습니다.");
            }
            else {
                OiLoginMemberVO memberDetail = new OiLoginMemberVO(member);
                map.put("status", true);
                map.put("memberInfo", memberDetail);
            }

        return map;
    }
}
