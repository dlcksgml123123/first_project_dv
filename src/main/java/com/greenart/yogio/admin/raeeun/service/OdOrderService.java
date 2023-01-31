package com.greenart.yogio.admin.raeeun.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.raeeun.vo.ListVO;
import com.greenart.yogio.admin.raeeun.vo.OptionVO;
import com.greenart.yogio.admin.raeeun.vo.OrderAddVO;
import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.mypage.member.repository.MpMemberInfoRepository;
import com.greenart.yogio.mypage.order.entity.MpMypageMenuChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOptionChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOrderPriceByOrderNumEntity;
import com.greenart.yogio.mypage.order.entity.MpOrderInfoEntity;
import com.greenart.yogio.mypage.order.entity.MpPlusMenuChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpPlusMenuEntity;
import com.greenart.yogio.mypage.order.repository.MpMypageMenuChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOptionChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOrderPriceByOrderNumRepository;
import com.greenart.yogio.mypage.order.repository.MpOrderInfoRepository;
import com.greenart.yogio.mypage.order.repository.MpPlusMenuChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpPlusMenuRepository;
import com.greenart.yogio.mypage.store.entity.MpMenuCategoryEntity;
import com.greenart.yogio.mypage.store.entity.MpMenuInfoEntity;
import com.greenart.yogio.mypage.store.entity.MpStoreInfoEntity;
import com.greenart.yogio.mypage.store.repository.MpMenuCategoryRepository;
import com.greenart.yogio.mypage.store.repository.MpMenuInfoRepository;
import com.greenart.yogio.mypage.store.repository.MpStoreInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class OdOrderService {
  @Autowired MpPlusMenuChoiceRepository plusChoiceRepo;
  @Autowired MpOrderInfoRepository oRepo;
  @Autowired MpMypageOptionChoiceRepository optionChoiceRepo;
  @Autowired MpMypageMenuChoiceRepository menuChoiceRepo;
  @Autowired MpStoreInfoRepository storeRepo;
  @Autowired MpMenuCategoryRepository menuCateRepo;
  @Autowired MpMypageOrderPriceByOrderNumRepository priceRepo;
  @Autowired MpMenuInfoRepository menuRepo;
  @Autowired MpPlusMenuRepository plustRepo;
  @Autowired MpMemberInfoRepository memberRepo;
  
  public Map<String, Object> showBriefOrderList(String keyword, @PageableDefault(size = 8) Pageable pageable) {
    // 멤버로 주문 내역 찾아서 메뉴 출력시, 주문 번호 별로 묶어야 함
    // 메뉴 옵션은 메뉴 주문 번호 별로 출력 해야함 
    Map<String, Object> map2 = new LinkedHashMap<>();

    // map을 저장할 수 있는 list 생성
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    if (keyword == null) {
      keyword = "";
    }
    // 모든 주문 메뉴 정보를 List에 저장
    List<MpMypageMenuChoiceEntity> mList = menuChoiceRepo.findOrderList(keyword);
    if (mList.isEmpty()) {
      map2.put("status", false);
    } else {
      for (int m = 0; m < mList.size(); m++) {
        Map<String, Object> map = new LinkedHashMap<>();
        // 메뉴 리스트의 메뉴 카테고리 값 저장
        MpMenuCategoryEntity menuCate = menuCateRepo.findByMcSeq(mList.get(m).getMcSeq());
        // 메뉴 카테고리의 값을 통해 가게 정보 저장 
        MpStoreInfoEntity store = storeRepo.findBySiSeq(menuCate.getStore().getSiSeq());
        MpOrderInfoEntity order = oRepo.findByOiSeq(mList.get(m).getOiSeq());
        // 주문 번호 map에 저장
        map.put("orderNum", order.getOiOrderNum());
        map.put("oiStatus", order.getOiStatus());
        // 가게 이름 map에 저장
        map.put("storeName", store.getSiName());
        // 주문한 메뉴 정보 저장
        map.put("menuName", mList.get(m).getMniName());
        // 주문일자, 배달완료일자 저장
        map.put("orderDate", order.getOiOrderDt());
        map.put("finishDate", order.getOiFinishDt());
        // 주문 가격 저장
        MpMypageOrderPriceByOrderNumEntity price = priceRepo.findByOiOrderNum(mList.get(m).getOiOrderNum());
        if (price.getTotalOptionPrice() != null) {
          map.put("price", price.getTotalMenuPrice() + price.getTotalOptionPrice());
        } else {
          map.put("price", price.getTotalMenuPrice());
        }
        list.add(map);
      }
      map2.put("status", true);
      map2.put("list", list);
    }
    return map2;
  }


  // 주문 번호를 통해 주문내역 출력
  public Map<String, Object> showOrder(String orderNum) {
    Map<String, Object> map = new LinkedHashMap<>();
    List<Object> list = new ArrayList<Object>();

    // 입력된 주문번호와 일치하는 메뉴 선택 리스트로 저장
    List<MpMypageMenuChoiceEntity> mlist = menuChoiceRepo.findByOiOrderNum(orderNum);

    if (mlist.isEmpty()) {
      map.put("status", false);
      map.put("message", "주문번호를 다시 확인해주세요.");
    }

    else {
      // 주문 번호 별 가게 이름, 주문 일자, 주문 금액
      // 첫번째 주문의 메뉴 카테고리를 들고와서 카테고리 정보찾고,
      MpMenuCategoryEntity menuCate = menuCateRepo.findByMcSeq(mlist.get(0).getMcSeq());
      // 카테고리 정보를 통해 가게 정보 저장
      MpStoreInfoEntity store = storeRepo.findBySiSeq(menuCate.getStore().getSiSeq());
      map.put("storeName", store.getSiName());
      MpOrderInfoEntity order = oRepo.findByOiSeq(mlist.get(0).getOiSeq());
      map.put("orderDate", order.getOiOrderDt());
      map.put("finishDate", order.getOiFinishDt());
      // 가격 정보를 출력할 수 있는 엔터티 들고와서
      MpMypageOrderPriceByOrderNumEntity price = priceRepo.findByOiOrderNum(mlist.get(0).getOiOrderNum());
      // 옵션가격이 있는 경우
      if (price.getTotalOptionPrice() != null) {
        // 합계 금액을 출력
        map.put("price", price.getTotalMenuPrice() + price.getTotalOptionPrice());
      }
      // 옵션가격이 없는 경우
      else {
        // 메뉴 가격만 출력
        map.put("price", price.getTotalMenuPrice());
      }
      // 반복문을 통해 주문한 메뉴 아래에 옵션 저장
      for (int i = 0; i < mlist.size(); i++) {
        List<MpMypageOptionChoiceEntity> option = optionChoiceRepo.findByOiSeq(mlist.get(i).getOiSeq());
        List<OptionVO> optionList = new ArrayList<OptionVO>();
        for (int j = 0; j < option.size(); j++) {
          OptionVO optionVo = OptionVO.builder().oiSeq(option.get(j).getOiSeq())
              .pmName(option.get(j).getPmName())
              .pmcAmount(option.get(j).getPmcAmount())
              .pmPrice(option.get(j).getPmPrice()).build();
          optionList.add(optionVo);
        }
        ListVO vo = new ListVO();
        if (optionList.isEmpty()) {
          vo = ListVO.builder()
          .oiSeq(mlist.get(i).getOiSeq())
          .oiOrderNum(mlist.get(i).getOiOrderNum())
          .miSeq(mlist.get(i).getMiSeq())
          .mniName(mlist.get(i).getMniName())
          .menuAmount(mlist.get(i).getMenuAmount())
          .menuPrice(mlist.get(i).getMenuPrice())
          .mcSeq(mlist.get(i).getMcSeq())
          .oiStatus(mlist.get(i).getOiStatus())
          .optionList(null)
          .build();
        }
        else {
          vo = ListVO.builder()
          .oiSeq(mlist.get(i).getOiSeq())
          .oiOrderNum(mlist.get(i).getOiOrderNum())
          .miSeq(mlist.get(i).getMiSeq())
          .mniName(mlist.get(i).getMniName())
          .menuAmount(mlist.get(i).getMenuAmount())
          .menuPrice(mlist.get(i).getMenuPrice())
          .mcSeq(mlist.get(i).getMcSeq())
          .oiStatus(mlist.get(i).getOiStatus())
          .optionList(optionList)
          .build();
        }
        // 리스트에 메뉴와 옵션을 저장
        list.add(vo);
      }
      // 주문메뉴와 옵션이 저장된 리스트 map 에 저장
      map.put("menu", list);
      map.put("status", true);
    }
    return map;
  }


  // 주문 내역 추가
  public Map<String, Object> addMenu(OrderAddVO data) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();

    // 선택한 메뉴 번호로 메뉴 정보를 저장
    MpMenuInfoEntity menu = menuRepo.findByMniSeq(data.getOiMniSeq());
    MbMemberInfoEntity member = memberRepo.findByMiSeq(data.getOiMiSeq());

    if (data.getOiMniSeq() == null) {
      map.put("status", false);
      map.put("message", "메뉴를 입력해 주세요.");
    } else if (data.getOiMiSeq() == null) {
      map.put("status", false);
      map.put("message", "회원번호를 입력해 주세요.");
    } else if (data.getOiMenuAmount() == null) {
      map.put("status", false);
      map.put("message", "메뉴 수량을 입력해 주세요.");
    } else if (data.getOiStatus() == null) {
      map.put("status", false);
      map.put("message", "주문 상태를 입력해 주세요.");
    }
    // 만약에 모든 정보가 다 입력이 되었고, oiStatus가 0이라면 장바구니 상태이므로
    else if (data.getOiStatus() == 0) {
      // 주문 entity 새로 생성
      MpOrderInfoEntity order = MpOrderInfoEntity.builder()
          .menu(menu).member(member)
          .oiStatus(data.getOiStatus())
          .oiMenuAmount(data.getOiMenuAmount())
          .oiOrderNum(null)
          .oiOrderDt(null)
          .oiFinishDt(null).build();

      // 저장
      oRepo.save(order);

      // 저장된 메뉴 map에 저장
      map.put("status", true);
      map.put("menu", order);
    }

    // 만약 oiStatus가 1이고, 결제 완료 상태라면
    else if (data.getOiStatus() == 1) {
      // 주문 entity 새로 생성
      MpOrderInfoEntity order = MpOrderInfoEntity.builder()
          .menu(menu).member(member)
          .oiStatus(data.getOiStatus())
          .oiMenuAmount(data.getOiMenuAmount())
          .oiOrderNum(data.getOiOrderNum())
          .oiOrderDt(data.getOiOrderDt())
          .oiFinishDt(null).build();

      // 저장
      oRepo.save(order);

      // 저장된 메뉴 map에 저장
      map.put("status", true);
      map.put("menu", order);
    }
    // 나머지 경우 -> 즉, 배달까지 완료된 상태라면
    else {
      // 주문 entity 새로 생성
      MpOrderInfoEntity order = MpOrderInfoEntity.builder()
          .menu(menu).member(member)
          .oiStatus(data.getOiStatus())
          .oiMenuAmount(data.getOiMenuAmount())
          .oiOrderNum(data.getOiOrderNum())
          .oiOrderDt(data.getOiOrderDt())
          .oiFinishDt(data.getOiFinishDt()).build();

      // 저장
      oRepo.save(order);
    }
    return map;
  }

  // 주문 옵션 추가
  public Map<String, Object> addOption(OrderAddVO data) {
    Map<String, Object> map = new LinkedHashMap<String, Object>();

    if (data.getPmcOiSeq() == null) {
      map.put("status", false);
      map.put("message", "주문번호를 입력해주세요");
    }
    else if (data.getPmcPmSeq() == null) {
      map.put("status", false);
      map.put("message", "옵션번호를 입력해주세요");
    }
    else if (data.getPmcAmount() == null) {
      map.put("status", false);
      map.put("message", "옵션수량을 입력해주세요");
    }
    else {
      MpOrderInfoEntity order = oRepo.findByOiSeq(data.getPmcOiSeq());
      // 선택한 옵션 번호로 옵션 메뉴의 정보를 저장
      MpPlusMenuEntity optionMenu = plustRepo.findByPmSeq(data.getPmcPmSeq());
      // 옵션 선택 테이블 새로 생성
      MpPlusMenuChoiceEntity option = MpPlusMenuChoiceEntity.builder().
      plusMenu(optionMenu).pmcAmount(data.getPmcAmount()).order(order).build();
      // 옵션 선택 저장
      plusChoiceRepo.save(option);
      // 저장된 메뉴 map에 저장
      map.put("status", true);
      map.put("option", option);
    }
    return map;
    }


  // 주문 상태 수정
  public void updateOrderStatus(Integer value, String orderNum) {
    List<MpOrderInfoEntity> entity = oRepo.findAllByOiOrderNum(orderNum);
    if (value == 0) {
      for (int i = 0; i < entity.size(); i++) {
        entity.get(i).setOiStatus(value);
        entity.get(i).setOiOrderNum(null);
        entity.get(i).setOiOrderDt(null);
        entity.get(i).setOiFinishDt(null);
        oRepo.save(entity.get(i));
      }
    } else if (value == 1) {
      for (int i = 0; i < entity.size(); i++) {
        entity.get(i).setOiStatus(value);
        oRepo.save(entity.get(i));
      }
    } else {
      for (int i = 0; i < entity.size(); i++) {
        entity.get(i).setOiStatus(value);
        oRepo.save(entity.get(i));
      }
    }
  }


  // 주문 내역 삭제
  public void deleteOrder(String orderNum) {
    // 주문번호가 일치하는 내역을 찾아서 저장
    List<MpOrderInfoEntity> entity = oRepo.findAllByOiOrderNum(orderNum);
    // 내역 안의 메뉴정보를 전부 삭제
    for (int i = 0; i < entity.size(); i++) {
      oRepo.deleteById(entity.get(i).getOiSeq());
      // 메뉴정보에 걸린 옵션 정보를 찾아서 저장
      List<MpPlusMenuChoiceEntity> option = plusChoiceRepo.findByOrder(entity.get(i));
      // 만약 옵션 선택이 있다면,
      if (!option.isEmpty()) {
        // 옵션의 수만큼 반복문을 돌려 옵션 삭제
        for (int j = 0; j < option.size(); j++) {
          plusChoiceRepo.delete(option.get(j));
        }
      }
    }
  }
}