package com.greenart.yogio.lchwork.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.greenart.yogio.lchwork.entity.OiCartViewEntity;
import com.greenart.yogio.lchwork.entity.OiDeliveryInfoEntity;
// import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentCompleteEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentEndEntity;
import com.greenart.yogio.lchwork.entity.OiPaymentInfoEntity;
import com.greenart.yogio.lchwork.repository.OiCartViewRepository;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentCompleteRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentEndRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentInfoRepository;
import com.greenart.yogio.lchwork.service.PaymentInfoService;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.service.MbMemberService;
import com.greenart.yogio.mypage.member.service.MpMemberService;
import com.greenart.yogio.mypage.order.entity.MpMypageMenuChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOptionChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOrderPriceByOiSeqEntity;
import com.greenart.yogio.mypage.order.repository.MpMypageMenuChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOptionChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOrderPriceByOiSeqRepository;
import com.greenart.yogio.mypage.store.entity.MpMenuCategoryEntity;
import com.greenart.yogio.mypage.store.entity.MpStoreInfoEntity;
import com.greenart.yogio.mypage.store.repository.MpMenuCategoryRepository;
import com.greenart.yogio.mypage.store.repository.MpStoreInfoRepository;

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
    @Autowired MpMypageMenuChoiceRepository menuChoiceRepo;
    @Autowired MpMenuCategoryRepository menuCateRepo;
    @Autowired MpStoreInfoRepository storeRepo;
    @Autowired MpMypageOptionChoiceRepository optionChoiceRepo;
    @Autowired MpMypageOrderPriceByOiSeqRepository priceOiSeqRepo;
    @Autowired OiCartViewRepository cartRepo;
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
    // @GetMapping("/complete") 
    // public ResponseEntity<Object> paymentCompleteInfo(@RequestParam Long miSeq, @RequestParam Long siSeq) {
    //     Map<String, Object> map = pService.showCompleteInfo(miSeq, siSeq);

    //     return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    // }
    // @GetMapping("/end")
    // public ResponseEntity<Object> paymentEndInfo(HttpSession session, String ) {
    //     Map<String, Object> map = pService.showCompleteInfo(miSeq, siSeq);

    //     return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    // }
    
    @GetMapping("/end")
    public Map<String, Object> showPayEndInfo(HttpSession session,  @RequestParam String orderNum ){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MbMemberInfoEntity loginMember = (MbMemberInfoEntity)session.getAttribute("loginUser");
        if(loginMember == null) {
            map.put("message", "로그인상태가 아닙니다.");
            return map;
        }
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

    @GetMapping("/cartinfo2")
    public Map<String, Object> getCartInfo2 (HttpSession session, @RequestParam Integer oiStatus) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        MbMemberInfoEntity loginMember = (MbMemberInfoEntity) session.getAttribute("loginUser");
        if (loginMember == null) {
            map.put("status", false);
            map.put("message", "로그인 후 이용하실 수 있습니다.");
            return map;
        } 
        OiCartViewEntity data = cartRepo.findByOiStatusAndMiSeq(oiStatus, loginMember.getMiSeq());
        if(data == null) {
            map.put("message", "장바구니가 비어있음");
        }
        else {
            map.put("message", "장바구니 조회성공");
            map.put("장바구니", data);
        }
        return map;
    }

    // @GetMapping("/cartinfo")
    // public Map<String, Object> getCartInfo (HttpSession session, @RequestParam Integer oiStatus) {
    //     Map<String, Object> map = new LinkedHashMap<String, Object>();
    //     MbMemberInfoEntity loginMember = (MbMemberInfoEntity) session.getAttribute("loginUser");
    //     if (loginMember == null) {
    //         map.put("status", false);
    //         map.put("message", "로그인 후 이용하실 수 있습니다.");
    //         return map;
    //     } 
    // }



    // 주문 번호를 통해 주문표 출력
    @GetMapping("/order")
    public Map<String, Object> showWishList (HttpSession session) {
        Map<String, Object> map = new LinkedHashMap<>();
        MbMemberInfoEntity member = (MbMemberInfoEntity) session.getAttribute("loginUser");
        if (member == null) {
        map.put("status", false);
        map.put("message", "로그인 후 이용하실 수 있습니다.");
        } 
        else {
        Map<String, Object> map2 = new LinkedHashMap<>();
        List<Object> list = new ArrayList<Object>();

        // 회원번호와 일치하는 메뉴 선택 리스트로 저장
        List<MpMypageMenuChoiceEntity> mlist = menuChoiceRepo.findByMiSeq(member.getMiSeq());
            if (mlist.isEmpty()) {
            map.put("status", false);
            map.put("message", "주문표가 비어있습니다.");
            }
        Integer totalPrice = 0;
        // 반복문으로 메뉴 선택 리스트를 돌면서
        for (int m = 0; m < mlist.size(); m++) {
        // 만약 메뉴 선택된 상태가 장바구니 상태라면
        if (mlist.get(m).getOiStatus() == 1) {
        // 첫번째 주문의 메뉴 카테고리를 들고와서 카테고리 정보찾고,
        MpMenuCategoryEntity menuCate = menuCateRepo.findByMcSeq(mlist.get(m).getMcSeq());
        // 카테고리 정보를 통해 가게 정보 저장
        MpStoreInfoEntity store = storeRepo.findBySiSeq(menuCate.getStore().getSiSeq());
        map2.put("storeName", store.getSiName());
        // 가격 정보를 출력할 수 있는 엔터티 들고와서
          
        // 주문한 메뉴 리스트에 저장
        list.add(mlist.get(m));
        // 저장된 메뉴의 옵션 리스트에 저장해서
        List<MpMypageOptionChoiceEntity> olist = optionChoiceRepo.findByOiSeq(mlist.get(m).getOiSeq());
        // 옵션 선택이 있는 경우, 옵션리스트 리스트에 저장
            if (!olist.isEmpty()) {
            list.add(olist);
            }
        }
        MpMypageOrderPriceByOiSeqEntity price = priceOiSeqRepo.findByOiSeq(mlist.get(m).getOiSeq());
        // 합계 금액을 출력
        list.add(price);
        totalPrice += price.getOiSeqPrice();
        // 만약 list가 비어있다면, 장바구니상태인 주문 내역이 없는 것이므로 
        if (list.isEmpty()) {
            Map<String, Object> map3 = new LinkedHashMap<String, Object>();
            map3.put("status", false);
            map3.put("message", "주문표가 비어있습니다.");
            return map3;
        }
        map2.put("order", list);
        }
    map2.put("totalPrice", totalPrice);
    // 주문메뉴와 옵션이 저장된 리스트 map 에 저장
    return map2;
        }    
    return map;
    }

  
}
