package com.greenart.yogio.member.review.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.review.entity.MbOrderInfoEntity;
import com.greenart.yogio.member.review.entity.MbReviewEntity;
import com.greenart.yogio.member.review.entity.MbReviewImageEntity;
import com.greenart.yogio.member.review.repository.MbOrderInfoRepository;
import com.greenart.yogio.member.review.repository.MbReviewImageRepository;
import com.greenart.yogio.member.review.repository.MbReviewRepository;
import com.greenart.yogio.member.review.vo.MbReviewVO;

import jakarta.servlet.http.HttpSession;

@Service
public class MbReviewService {
    @Autowired MbReviewRepository r_repo;
    @Autowired MbReviewImageRepository i_repo;
    @Autowired MbOrderInfoRepository o_repo;
    public Map<String, Object> addReview(MbReviewVO data, HttpSession session) { //리뷰쓰기
     Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
     MbMemberInfoEntity loginUser = (MbMemberInfoEntity)session.getAttribute("loginUser");
     MbOrderInfoEntity order = o_repo.findByOiSeq(data.getOiSeq());
     
     Date day = new Date();
     Calendar today = Calendar.getInstance();
     today.setTime(day); 
     Calendar regDt = Calendar.getInstance();
     regDt.setTime(order.getOiFinishDt());
     Long diffSec = (today.getTimeInMillis() - regDt.getTimeInMillis())/1000;
     Long diffDay = diffSec / (24 * 60 * 60);

     if(loginUser == null) {
        resultMap.put("status", false);
        resultMap.put("message", "로그인 후 사용가능합니다");
        resultMap.put("code",HttpStatus.BAD_REQUEST);
        return resultMap;
     }
     
     //로그인한 유저의 번호와 주문정보의 회원번호가 다를시 잘못된 접근이라고 출력
     

     else if(r_repo.countByReOiSeq(data.getOiSeq())>=1) {
        resultMap.put("status", false);
        resultMap.put("message", "이미 리뷰가 등록되었습니다");
        resultMap.put("code",HttpStatus.BAD_REQUEST);
     }

     else if (diffDay > 7) {
      resultMap.put("status", false);
      resultMap.put("message", "주문이 완료된 날짜에서 1주까지만 리뷰 등록이 가능합니다.");
      resultMap.put("code",HttpStatus.BAD_REQUEST);
    }
     else {
        MbReviewEntity review = MbReviewEntity.builder()
        .reRegDt(data.getRegDt())
        .reScore(data.getScore())
        .reContent(data.getContent())
        .reOiSeq(data.getOiSeq())
        .reTasteScore(data.getTasteScore())
        .reQuantityScore(data.getQuantityScore())
        .reDeliveryScore(data.getDeliveryScore())
        .build();
        r_repo.save(review);

        MbReviewImageEntity image = MbReviewImageEntity.builder()
        .riName(data.getName())
        .riOrder(data.getOrder())
        .riReSeq(review)
        .build();
          i_repo.save(image);

          resultMap.put("status", true);
          resultMap.put("message", "리뷰가 등록되었습니다");
          resultMap.put("code", HttpStatus.CREATED);
        }
        return resultMap;
    }
}
