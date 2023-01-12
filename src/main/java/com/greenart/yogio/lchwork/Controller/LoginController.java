package com.greenart.yogio.lchwork.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.member.repository.MemberInfoRepository;

@RestController("/member")
public class LoginController {
    // @Autowired MemberInfoRepository miRepo;
    // @PostMapping("/login")
    // public ResponseEntity<Object> memberLogin(@RequestBody)
}
