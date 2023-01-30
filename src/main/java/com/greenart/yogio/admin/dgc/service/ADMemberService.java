package com.greenart.yogio.admin.dgc.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.dgc.vo.ADMemberAddVO;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.repository.MbMemberInfoRepository;
import com.greenart.yogio.member.vo.MbMemberVO;
import com.greenart.yogio.utils.MbAESAlgorithm;

@Service
public class ADMemberService {
  @Autowired MbMemberInfoRepository m_repo;
  public Map<String, Object> addMember(ADMemberAddVO data) { //회원가입
   Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
   String id_pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
   String pwd_pattern = "^[a-zA-Z0-9!@#$%^&*()-_=+]*$";
   if(m_repo.countByMiId(data.getId())!=0) { 
       resultMap.put("status", false);
       resultMap.put("message",data.getId()+"은/는 이미 등록된 사용자 입니다");
   }
   else if(data.getId() == null || data.getId().equals("")) {
    resultMap.put("status",false);
    resultMap.put("message", "아이디를 입력하세요");
  }
   else if(data.getPwd() == null || data.getPwd().equals("")) {
    resultMap.put("status",false);
    resultMap.put("message", "비밀번호를 입력하세요");
  }
   else if(data.getEmail() == null || data.getEmail().equals("")) {
    resultMap.put("status",false);
    resultMap.put("message", "이메일을 입력하세요");
  }
   else if(data.getPhone() == null || data.getPhone().equals("")) {
    resultMap.put("status",false);
    resultMap.put("message", "전화번호를 입력하세요");
  }
   else if(data.getNickname() == null || data.getNickname().equals("")) {
    resultMap.put("status",false);
    resultMap.put("message", "이름을 입력하세요");
  }
   else if(data.getAddress() == null || data.getAddress().equals("")) {
    resultMap.put("status",false);
    resultMap.put("message", "주소를 입력하세요");
  }
   else if(m_repo.countByMiEmail(data.getEmail())>=1) { 
       resultMap.put("status", false);
       resultMap.put("message",data.getEmail()+"은/는 이미 등록된 이메일입니다");
   }
   else if(m_repo.countByMiPhone(data.getPhone())>=1) { 
       resultMap.put("status", false);
       resultMap.put("message",data.getPhone()+"은/는 이미 등록된 전화번호입니다");
   }
   else if(data.getPwd().length()<8) {
     resultMap.put("status", false);
     resultMap.put("message", "비밀번호는 8자리 이상입니다");
   }
   else if(!Pattern.matches(pwd_pattern, data.getPwd())) {
     resultMap.put("status", false); 
     resultMap.put("message", "비밀번호에 공백문자를 사용 할 수 없습니다");
   } 
   else if(!Pattern.matches(id_pattern, data.getId())) {
     resultMap.put("status", false);
     resultMap.put("message", "아이디에 공백문자나 특수문자를 사용 할 수 없습니다");
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
   }
   return resultMap;
  }

  public Map<String,Object> getMemberList(Pageable pageable, String keyword){
    // Page<MbMemberInfoEntity> page = m_repo.findAll(pageable);
    Page<MbMemberInfoEntity> page = m_repo.findByMiIdContains(keyword, pageable);
    Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("status", true);
    resultMap.put("list", page.getContent());
    resultMap.put("total", page.getTotalElements());
    resultMap.put("totalpage", page.getTotalPages());
    resultMap.put("currentPage", page.getNumber());
    resultMap.put("memberList", page);
    return resultMap;
  }
  public void updateMemberStatus(Integer value, Long member_no) {
    MbMemberInfoEntity entity = m_repo.findById(member_no).get();
    entity.setMiStatus(value);
    m_repo.save(entity);
}
  public void deleteMember (Long member_no) {
    m_repo.deleteById(member_no);
}
}
