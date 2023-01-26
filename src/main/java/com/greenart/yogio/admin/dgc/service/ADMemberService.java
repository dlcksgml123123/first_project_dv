package com.greenart.yogio.admin.dgc.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.dgc.vo.ADMemberAddVO;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.repository.MbMemberInfoRepository;
import com.greenart.yogio.utils.MbAESAlgorithm;

@Service
public class ADMemberService {
  @Autowired MbMemberInfoRepository m_repo;
  public Map<String, Object> addMember(ADMemberAddVO data) { //회원가입
   Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
   String id_pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
   String pwd_pattern = "^[a-zA-Z0-9!@#$%^&*()-_=+]*$";
   if(m_repo.countByMiId(data.getId())>=1) { 
       resultMap.put("status", false);
       resultMap.put("message",data.getId()+"은/는 이미 등록된 사용자 입니다");
       resultMap.put("code", HttpStatus.BAD_REQUEST);
   }
   else if(m_repo.countByMiEmail(data.getEmail())>=1) { 
       resultMap.put("status", false);
       resultMap.put("message",data.getEmail()+"은/는 이미 등록된 이메일입니다");
       resultMap.put("code", HttpStatus.BAD_REQUEST);
   }
   else if(m_repo.countByMiPhone(data.getPhone())>=1) { 
       resultMap.put("status", false);
       resultMap.put("message",data.getPhone()+"은/는 이미 등록된 전화번호입니다");
       resultMap.put("code", HttpStatus.BAD_REQUEST);
   }
   else if(data.getPwd().length()<8) {
     resultMap.put("status", false);
     resultMap.put("message", "비밀번호는 8자리 이상입니다");
     resultMap.put("code", HttpStatus.BAD_REQUEST);
   }
   else if(!Pattern.matches(pwd_pattern, data.getPwd())) {
     resultMap.put("status", false); 
     resultMap.put("message", "비밀번호에 공백문자를 사용 할 수 없습니다");
     resultMap.put("code", HttpStatus.BAD_REQUEST);
   } 
   else if(!Pattern.matches(id_pattern, data.getId())) {
     resultMap.put("status", false);
     resultMap.put("message", "아이디에 공백문자나 특수문자를 사용 할 수 없습니다");
     resultMap.put("code", HttpStatus.BAD_REQUEST);
   } 
   else {
     try {
       String encPwd = MbAESAlgorithm.Encrypt(data.getPwd());
       data.setPwd(encPwd);
     }  catch(Exception e) {e.printStackTrace();}
     MbMemberInfoEntity entity = MbMemberInfoEntity.builder()
     .miId(data.getId()).miPwd(data.getPwd()).miEmail(data.getEmail())
     .miPhone(data.getPhone()).miNickname(data.getNickname())
     .miAddress(data.getAddress()).miStatus(data.getStatus()).build();
     m_repo.save(entity);
     resultMap.put("status", true);
     resultMap.put("message", "회원이 등록되었습니다");
     resultMap.put("code", HttpStatus.CREATED);
   }
   return resultMap;
  }
}
