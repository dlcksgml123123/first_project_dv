package com.greenart.yogio.lchwork.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.lchwork.entity.OiDeliveryInfoEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentInfoEntity;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentInfoRepository;
import com.greenart.yogio.lchwork.service.PaymentInfoService;

// @RestController
// @RequestMapping("/paymentpage")
// public class OrderController {
//     // @Autowired PaymentInfoService pService;
//     @Autowired OiPaymentInfoRepository piRepo;
//     @Autowired OiMemberInfoRepository miRepo;
//     // @RequestParam @Nullable String piRequirement,
//     // @RequestParam Integer piPayWay,,@RequestParam Long piOiSeq
//     @PutMapping("/input")
//     public Map<String, Object> putPaymentInfo(
//         @RequestBody OiPaymentInfoEntity data
//     ){
//         Map<String, Object> map = new LinkedHashMap<>();
//         piRepo.save(data);
//         map.put("data", data);
//         return map;
//     }
//     @GetMapping("deliveryinfo")
//     public Map<String, Object> getDeliveryInfo(
    
// }
