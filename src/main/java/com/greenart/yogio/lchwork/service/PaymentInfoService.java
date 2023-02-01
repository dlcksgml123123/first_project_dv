package com.greenart.yogio.lchwork.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentCompleteEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentEndEntity;
import com.greenart.yogio.lchwork.repository.OiDeliveryInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiMenuInfoRepository;
import com.greenart.yogio.lchwork.repository.OiOrderInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentCompleteRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentEndRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPlusCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiPlusCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiPlusMenuChoiceRepository;
import com.greenart.yogio.lchwork.repository.OiPlusMenuRepository;
import com.greenart.yogio.lchwork.repository.OiStoreInfoRepository;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;

import jakarta.servlet.http.HttpSession;

@Service
public class PaymentInfoService {
    @Autowired OiMemberInfoRepository miRepo;
    @Autowired OiPaymentInfoRepository piRepo;
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired OiDeliveryInfoRepository diRepo;
    @Autowired OiMenuCategoryRepository mcRepo;
    @Autowired OiMenuCateJoinRepository mcjRepo;
    @Autowired OiMenuInfoRepository mniRepo;
    @Autowired OiPlusCategoryRepository pcRepo;
    @Autowired OiPlusCateJoinRepository pcjRepo;
    @Autowired OiPlusMenuRepository pmRepo;
    @Autowired OiPlusMenuChoiceRepository pmcRepo;
    @Autowired OiOrderInfoRepository oiRepo;
    @Autowired OiPaymentCompleteRepository paycomRepo;
    @Autowired OiPaymentEndRepository payEndRepo;

    // public Map<String, Object> showCompleteInfo(Long miSeq, Long siSeq){ - 로그인중인 멤버로 바꿈
    //     Map<String, Object> map = new LinkedHashMap<String, Object>();
    //     OiPaymentCompleteEntity data = paycomRepo.findByMiSeqAndSiSeq(miSeq, siSeq);
    //     if(data == null) {
    //         map.put("message", "조회할 결제정보가 없음");
    //         map.put("code", HttpStatus.BAD_REQUEST);
    //     }
    //     else {
    //         map.put("message", "결제정보 조회성공");
    //         map.put("data", data);
    //         map.put("code", HttpStatus.OK);
    //     }
    //     return map;
    // }
    // public Map<String, Object> showPayEndInfo(HttpSession session, String orderNum ){ -컨트롤러에 바로
    //     Map<String, Object> map = new LinkedHashMap<String, Object>();
    //     MbMemberInfoEntity loginMember = (MbMemberInfoEntity)session.getAttribute("loginUser");
    //     OiPaymentEndEntity data = payEndRepo.findByOiOrderNumAndMiSeq(orderNum, loginMember.getMiSeq());
    //     if(data == null) {
    //         map.put("message", "조회할 결제정보가 없음");
    //         map.put("code", HttpStatus.BAD_REQUEST);
    //     }
    //     else {
    //         map.put("message", "결제정보 조회성공");
    //         map.put("data", data);
    //         map.put("code", HttpStatus.OK);
    //     }
    //     return map;
    // }

    

}
