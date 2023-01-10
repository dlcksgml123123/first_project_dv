package com.greenart.yogio.review.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.review.entity.MbReviewEntity;
import com.greenart.yogio.review.entity.MbReviewImageEntity;
import com.greenart.yogio.review.entity.MbViewEntity;
import com.greenart.yogio.review.repository.MbReviewImageRepository;
import com.greenart.yogio.review.repository.MbReviewRepository;
import com.greenart.yogio.review.repository.MbViewRepository;
import com.greenart.yogio.review.vo.MbReviewVO;

import jakarta.servlet.http.HttpSession;

@Service
public class MbReviewService {
    @Autowired MbViewRepository r_repo;
    public Map<String, Object> addReview(MbViewEntity data, HttpSession session) { //리뷰쓰기
     Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
     MbMemberInfoEntity loginUser = (MbMemberInfoEntity)session.getAttribute("loginUser");
     if(loginUser == null) {
        resultMap.put("status", false);
        resultMap.put("message", "로그인 후 사용가능합니다");
        resultMap.put("code",HttpStatus.BAD_REQUEST);
        return resultMap;
     }
      r_repo.save(data);
      resultMap.put("status", true);
      resultMap.put("message", "리뷰가 등록되었습니다");
      resultMap.put("code", HttpStatus.CREATED);
      return resultMap;
    }
}
