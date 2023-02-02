package com.greenart.yogio.member.service;

import java.lang.reflect.Member;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.repository.MbMemberInfoRepository;
import com.greenart.yogio.member.vo.MbLoginVO;
import com.greenart.yogio.member.vo.MbMemberVO;
import com.greenart.yogio.utils.MbAESAlgorithm;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class MbMemberService {
   @Autowired MbMemberInfoRepository m_repo;
   public Map<String, Object> addMember(MbMemberVO data) { //회원가입
    Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
    String id_pattern = "^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$";
    String pwd_pattern = "^[a-zA-Z0-9!@#$%^&*()-_=+]*$";
    if(m_repo.countByMiId(data.getMiId())>=1) { 
        resultMap.put("status", false);
        resultMap.put("message",data.getMiId()+"은/는 이미 등록된 사용자 입니다");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else if(m_repo.countByMiEmail(data.getMiEmail())>=1) { 
        resultMap.put("status", false);
        resultMap.put("message",data.getMiEmail()+"은/는 이미 등록된 이메일입니다");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else if(m_repo.countByMiPhone(data.getMiPhone())>=1) { 
        resultMap.put("status", false);
        resultMap.put("message",data.getMiPhone()+"은/는 이미 등록된 전화번호입니다");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else if(data.getMiPwd().length()<8) {
      resultMap.put("status", false);
      resultMap.put("message", "비밀번호는 8자리 이상입니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else if(!Pattern.matches(pwd_pattern, data.getMiPwd())) {
      resultMap.put("status", false); 
      resultMap.put("message", "비밀번호에 공백문자를 사용 할 수 없습니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    } 
    else if(!Pattern.matches(id_pattern, data.getMiId())) {
      resultMap.put("status", false);
      resultMap.put("message", "아이디에 공백문자나 특수문자를 사용 할 수 없습니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    } 
    else {
      try {
        String encPwd = MbAESAlgorithm.Encrypt(data.getMiPwd());
        data.setMiPwd(encPwd);
      }  catch(Exception e) {e.printStackTrace();}
      if (data.getMiStatus() == null) {
        data.setMiStatus(0);
      }
      MbMemberInfoEntity member = MbMemberInfoEntity.builder()
        .miId(data.getMiId())
        .miPwd(data.getMiPwd())
        .miEmail(data.getMiEmail())
        .miPhone(data.getMiPhone())
        .miNickname(data.getMiNickname())
        .miAddress(data.getMiAddress())
        .miStatus(data.getMiStatus())
        .build();
      m_repo.save(member);
      resultMap.put("status", true);
      resultMap.put("message", "회원이 등록되었습니다");
      resultMap.put("code", HttpStatus.CREATED);
    }
    return resultMap;
}
public Map<String, Object> loginMember(MbLoginVO data) { //로그인
    Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
    MbMemberInfoEntity loginUser = null; 
    try {
      loginUser = m_repo.findTop1ByMiIdAndMiPwd(
      data.getMiId(), MbAESAlgorithm.Encrypt(data.getMiPwd())
      );
    }catch(Exception e) {e.printStackTrace();}
    if(loginUser == null) {
      resultMap.put("status", false);
      resultMap.put("message", "아이디 또는 비밀번호 오류입니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    }

    else if(loginUser.getMiStatus()!=0) {
      resultMap.put("status", false);
      resultMap.put("message", "활동이 정지되거나 탈퇴처리된 회원입니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    }

    else {
      resultMap.put("status", true);
      resultMap.put("message", "로그인 되었습니다");
      resultMap.put("code", HttpStatus.ACCEPTED);
      resultMap.put("loginUser", loginUser);
    }
    return resultMap;
  }

  public Map<String, Object> dupchkMemberId(MbMemberVO data) { //아이디중복조회
    Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
    if(m_repo.countByMiId(data.getMiId())!=0) { 
        resultMap.put("status", false);
        resultMap.put("message",data.getMiId()+"은/는 이미 등록된 아이디 입니다");
        resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else {
        resultMap.put("status", true);
        resultMap.put("message",data.getMiId()+"은/는 사용 가능한 아이디 입니다");
        resultMap.put("code", HttpStatus.OK);
    }
    return resultMap;
    } 

  public Map<String, Object> searchMemberId(MbMemberVO data) { //아이디찾기
    Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
    // 사용자 전화번호 받아서 리스트에 있는 것과 비교하여 해당 전화번호에 맞는 아이디 찾기
    MbMemberInfoEntity User = null; 
    User = m_repo.findByMiPhone(data.getMiPhone());
    if(User == null) {
      resultMap.put("status", false);
      resultMap.put("message", "해당하는 정보가 없습니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else{
      resultMap.put("status", true);
      resultMap.put("message", "고객님의 아이디를 찾았습니다");
      resultMap.put("code", HttpStatus.OK);
      resultMap.put("UserId", User.getMiId());
    }
    return resultMap;
  }

  public Map<String, Object> searchMemberPwd(MbMemberVO data) throws Exception{ //비밀번호찾기
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    MbMemberInfoEntity User = null;
    try {
    User = m_repo.findByMiIdAndMiPhone(data.getMiId(),data.getMiPhone());
   }catch(Exception e) {e.printStackTrace();}
    if(User == null) {
      resultMap.put("status", false);
      resultMap.put("message", "해당하는 정보가 없습니다");
      resultMap.put("code", HttpStatus.BAD_REQUEST);
    }
    else{
      resultMap.put("status", true);
      resultMap.put("message", "고객님의 비밀번호를 찾았습니다");
      resultMap.put("code", HttpStatus.OK);
      resultMap.put("UserPwd", MbAESAlgorithm.Decrypt(User.getMiPwd()));
    }
    return resultMap;
  }
  
  public Map<String, Object> deleteMember(Long miSeq) { //회원탈퇴
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    MbMemberInfoEntity User = m_repo.findByMiSeq(miSeq);
    if(User == null) {
      resultMap.put("status", false);
      resultMap.put("message", "해당 회원이 존재하지 않습니다.");
      resultMap.put("code",HttpStatus.BAD_REQUEST);
    }
    // MbMemberInfoEntity loginUser = (MbMemberInfoEntity)session.getAttribute("loginUser");
    // MbMemberInfoEntity User = null;
    // User = m_repo.findTop1ByMiPwd(MbAESAlgorithm.Encrypt(data.getMiPwd()));
    // MbMemberInfoEntity loginUser = null; 
    // try {
    //   loginUser = m_repo.findTop1ByMiIdAndMiPwd(
    //   data.getMiId(), MbAESAlgorithm.Encrypt(data.getMiPwd())
    //   );
    // }catch(Exception e) {e.printStackTrace();}
    // if(loginUser == null) {
    //   resultMap.put("status", false);
    //   resultMap.put("message", "로그인 후 사용가능합니다");
    //   resultMap.put("code",HttpStatus.BAD_REQUEST);
    //   return resultMap;
    // }
    // else if(User==null) {
    //   resultMap.put("status", false);
    //   resultMap.put("message", "잘못된 비밀번호입니다");
    //   resultMap.put("code",HttpStatus.BAD_REQUEST);
    // } 
    // else if(!loginUser.getMiPwd().equals(User.getMiPwd())) {
    //   resultMap.put("status", false);
    //   resultMap.put("message", "잘못된 비밀번호입니다");
    //   resultMap.put("code",HttpStatus.BAD_REQUEST);
    // } 
    // else {
      // loginUser.setMiStatus(3); //상태값 변경
      // m_repo.save(loginUser); //변경한 값 저장
      m_repo.delete(User);
      resultMap.put("status", true);
      resultMap.put("message", "회원정보가 삭제 되었습니다");
      resultMap.put("code",HttpStatus.OK);
    // }
    return resultMap;
  }
}
