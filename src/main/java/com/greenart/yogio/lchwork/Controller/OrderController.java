package com.greenart.yogio.lchwork.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.lchwork.entity.OiDeliveryInfoEntity;
import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentCompleteEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentEndEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentInfoEntity;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentCompleteRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentEndRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentInfoRepository;
import com.greenart.yogio.lchwork.service.PaymentInfoService;
import com.greenart.yogio.member.service.MbMemberService;
import com.greenart.yogio.mypage.member.service.MpMemberService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/paymentpage")
public class OrderController {
    @Autowired PaymentInfoService pService;
    @Autowired OiPaymentInfoRepository piRepo;
    @Autowired MbMemberService mbmService;
    @Autowired MpMemberService mpmService;
    @Autowired OiPaymentEndRepository peRepo;
    // @Autowired OiPaymentCompleteRepository payRepo;
    // @RequestParam @Nullable String piRequirement,
    // @RequestParam Integer piPayWay,,@RequestParam Long piOiSeq
    @PutMapping("/input")
    public Map<String, Object> putPaymentInfo(
        @RequestBody OiPaymentInfoEntity data
    ){
        Map<String, Object> map = new LinkedHashMap<>();
        piRepo.save(data);
        map.put("data", data);
        return map;
    }
    // @GetMapping("/deliveryinfo")
    // public String getDeliveryInfo(@RequestParam Long,  HttpSession session) {
        
    // }
    @GetMapping("/complete")
    public ResponseEntity<Object> paymentCompleteInfo(@RequestParam Long miSeq, @RequestParam Long siSeq) {
        Map<String, Object> map = pService.showCompleteInfo(miSeq, siSeq);

        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    // @GetMapping("/end")
    // public ResponseEntity<Object> paymentEndInfo(HttpSession session, String ) {
    //     Map<String, Object> map = pService.showCompleteInfo(miSeq, siSeq);

    //     return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    // }
    @GetMapping("/end")
    public Map<String, Object> showPayEndInfo(HttpSession session,  @RequestParam String orderNum ){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        OiMemberInfoEntity loginMember = (OiMemberInfoEntity)session.getAttribute("loginUser");
        OiPaymentEndEntity data = peRepo.findByOiOrderNumAndMiSeq(orderNum, loginMember.getMiSeq());
        if(data == null) {
            map.put("message", "조회할 결제정보가 없음");
            // map.put("code", HttpStatus.BAD_REQUEST);
        }
        else {
            map.put("message", "결제정보 조회성공");
            map.put("data", data);
            // map.put("code", HttpStatus.OK);
        }
        return map;
    }
}
