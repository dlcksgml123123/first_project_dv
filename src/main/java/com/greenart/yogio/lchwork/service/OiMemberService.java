package com.greenart.yogio.lchwork.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.vo.OiLoginMemberVO;
import com.greenart.yogio.lchwork.vo.OiLoginVO;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.utils.MbAESAlgorithm;

import jakarta.servlet.http.HttpSession;

@Service
public class OiMemberService {
    @Autowired OiMemberInfoRepository oimRepo;
    public Map<String, Object> addMember(MbMemberInfoEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(oimRepo.countByMiId(data.getMiId()) >= 1) {
            resultMap.put("status", false);
            resultMap.put("message", data.getMiId()+"는 이미 등록된 사용자 입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else {
            try {
                String encPwd = MbAESAlgorithm.Encrypt(data.getMiPwd());
                data.setMiPwd(encPwd);
              }  catch(Exception e) {e.printStackTrace();}
            //   String encPwd = MbAESAlgorithm.Encrypt(data.getMiPwd());
            //   data.setMiPwd(encPwd);
            oimRepo.save(data);
            resultMap.put("status", true);
            resultMap.put("message", "회원등록 완료!");
            resultMap.put("code", HttpStatus.CREATED);
        }
        return resultMap;
    }
    public Map<String, Object> LoginMember(OiLoginVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MbMemberInfoEntity loginUser = null;
        loginUser = oimRepo.findByMiIdAndMiPwd(data.getMiId(), MbAESAlgorithm.Encrypt(data.getMiPwd()));
        if(loginUser == null) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 또는 비밀번호 오류!");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }
        else {
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
            resultMap.put("loginUser", loginUser);
        }
        return resultMap;
    }
    
}
