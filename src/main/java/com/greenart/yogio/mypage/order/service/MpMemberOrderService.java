package com.greenart.yogio.mypage.order.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageMenuChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOptionChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOrderPriceByOiSeqEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOrderPriceByOrderNumEntity;
import com.greenart.yogio.mypage.order.entity.MpOrderInfoEntity;
import com.greenart.yogio.mypage.order.repository.MpMypageMenuChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOptionChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOrderPriceByOiSeqRepository;
import com.greenart.yogio.mypage.order.repository.MpMypageOrderPriceByOrderNumRepository;
import com.greenart.yogio.mypage.order.repository.MpOrderInfoRepository;
import com.greenart.yogio.mypage.order.repository.MpPlusMenuChoiceRepository;
import com.greenart.yogio.mypage.order.repository.MpPlusMenuRepository;
import com.greenart.yogio.mypage.order.vo.MpOrderInfoVO;
import com.greenart.yogio.mypage.order.vo.MpWishListVO;
import com.greenart.yogio.mypage.store.entity.MpMenuCategoryEntity;
import com.greenart.yogio.mypage.store.entity.MpStoreInfoEntity;
import com.greenart.yogio.mypage.store.repository.MpMenuCategoryRepository;
import com.greenart.yogio.mypage.store.repository.MpStoreInfoRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class MpMemberOrderService {
  @Autowired MpOrderInfoRepository oRepo;
  @Autowired MpPlusMenuChoiceRepository choiceRepo;
  @Autowired MpPlusMenuRepository plusMenuRepo;
  @Autowired MpMypageOptionChoiceRepository optionChoiceRepo;
  @Autowired MpMypageMenuChoiceRepository menuChoiceRepo;
  @Autowired MpStoreInfoRepository storeRepo;
  @Autowired MpMenuCategoryRepository menuCateRepo;
  @Autowired MpMypageOrderPriceByOrderNumRepository priceRepo;
  @Autowired MpMypageOrderPriceByOiSeqRepository priceOiSeqRepo;

  // 멤버별 상세한 주문 내역 출력
  public Map<String, Object> showOrderList(HttpSession session, Pageable pageable) {
    Map<String, Object> map = new LinkedHashMap<>();
    MbMemberInfoEntity member = (MbMemberInfoEntity) session.getAttribute("loginUser");
    if (member == null) {
      map.put("status", false);
      map.put("message", "로그인 후 이용하실 수 있습니다.");
    }
    // 로그인 정보가 있다면
    else {
      // 주문 정보를 출력할 map 생성
      Map<String, Object> map2 = new LinkedHashMap<>();

      // 멤버로 주문 내역 찾아서 메뉴 출력시, 주문 번호 별로 묶어야 함
      // 메뉴 옵션은 메뉴 주문 번호 별로 출력 해야함 

      // map을 저장할 수 있는 리스트 생성
      List<Map<String,Object>> orderlist = new ArrayList<Map<String,Object>>();

      // 멤버 변수를 통해 멤버가 주문한 메뉴 정보를 List에 저장
      List<MpMypageMenuChoiceEntity> mList = menuChoiceRepo.findByMiSeq(member.getMiSeq());
      // 만약 주문한 메뉴 정보 리스트가 없다면, 주문내역이 없다는 메세지 출력
      if (mList.isEmpty()) {
        map2.put("status", false);
        map2.put("message", "주문내역 없음");
      } 
      // 주문 내역이 있다면,
      else {
        // 반복문을 통해 멤버가 주문한 메뉴 정보 mList에서 주문 번호를 가져와서 (주문 멤버별로 엮여있음)
        for (int m = 0; m < mList.size(); m++) {
          if (mList.get(m).getOiStatus() == 2) {
            // 주문번호를 통해 주문한 메뉴정보를 조회해서 meList에 다시 저장 (주문 번호 별로 엮여있음)
            List<MpMypageMenuChoiceEntity> meList = menuChoiceRepo.findByOiOrderNum(mList.get(m).getOiOrderNum());
            
            // 임의의 변수를 지정
            int count = 0;
            // 반복문을 통해, 리스트 안에 저장된 값의 주문번호가 새로 저장될 값의 주문번호가 일치하는 경우가 있다면,
            // 변수의 값을 1높이고 반복문을 멈추게 설정
            for (int i = 0; i < m; i++) {
              if (mList.get(i).getOiOrderNum().equals(mList.get(m).getOiOrderNum())) {
                count++;
                break;
              }
            }
            // 반복문을 다 돌고 나온 후, 만약 변수의 값이 1이 아니라면, 같은 주문번호의 주문이 없는 것이므로 
            // list에 저장
            if (count != 1) {
              List<MpOrderInfoVO> orderMenu = new ArrayList<MpOrderInfoVO>();

              // 반복문을 통해서 하나의 메뉴를 vo에 저장하고, 그 메뉴의 옵션을 저장
              for (int j = 0; j < meList.size(); j++) {
                List<MpMypageOptionChoiceEntity> option = optionChoiceRepo.findByOiSeq(meList.get(j).getOiSeq());
                MpOrderInfoVO vo = new MpOrderInfoVO();
                if (option.isEmpty()) {
                  vo = MpOrderInfoVO.builder()
                  .oiSeq(meList.get(j).getOiSeq())
                  .oiOrderNum(meList.get(j).getOiOrderNum())
                  .miSeq(meList.get(j).getMiSeq())
                  .mniName(meList.get(j).getMniName())
                  .menuAmount(meList.get(j).getMenuAmount())
                  .menuPrice(meList.get(j).getMenuPrice())
                  .mcSeq(meList.get(j).getMcSeq())
                  .oiStatus(meList.get(j).getOiStatus())
                  .optionList(null)
                  .build();
                }
                else {
                  vo = MpOrderInfoVO.builder()
                  .oiSeq(meList.get(j).getOiSeq())
                  .oiOrderNum(meList.get(j).getOiOrderNum())
                  .miSeq(meList.get(j).getMiSeq())
                  .mniName(meList.get(j).getMniName())
                  .menuAmount(meList.get(j).getMenuAmount())
                  .menuPrice(meList.get(j).getMenuPrice())
                  .mcSeq(meList.get(j).getMcSeq())
                  .oiStatus(meList.get(j).getOiStatus())
                  .optionList(optionChoiceRepo.findByOiSeq(meList.get(j).getOiSeq()))
                  .build();
                }
                // 리스트에 메뉴와 옵션을 저장
                orderMenu.add(vo);
               
                // 만약 주문번호별 리스트의 마지막 주문이라면, orderlist에 저장함으로써 주문번호별로 구분해줌.
                if (j == meList.size() - 1) {
                  // 반복문이 돌때 마다 map3가 새로 생성될 수 있도록, 반복문안에 생성문 작성
                  Map<String, Object> map3 = new LinkedHashMap<String, Object>();
                  // map에 메뉴와 옵션정보가 저장된 list를 저장
                  map3.put("orderMenu", orderMenu);

                  // 주문 번호 별 가게 이름, 주문 일자, 주문 금액
                  // 메뉴 리스트의 카테고리 번호를 조회해서 메뉴 카테고리 정보를 가져오고
                  MpMenuCategoryEntity menuCate = menuCateRepo.findByMcSeq(meList.get(j).getMcSeq());
                  // 메뉴 카테고리 정보를 조회해서 가게 정보를 가져옴
                  MpStoreInfoEntity store = storeRepo.findBySiSeq(menuCate.getStore().getSiSeq());
                  // 가게 정보를 map에 저장
                  map3.put("storeName", store.getSiName());
                  // 주문정보번호 조회를 통해 주문 정보를 가져옴
                  MpOrderInfoEntity order = oRepo.findByOiSeq(meList.get(j).getOiSeq());
                  // 주문 번호의 주문날짜와 배달완료 날짜 map에 저장
                  map3.put("orderDate", order.getOiOrderDt());
                  map3.put("finishDate", order.getOiFinishDt());
                  // 주문번호를 조회해서 가격정보를 가져와서 
                  MpMypageOrderPriceByOrderNumEntity price = priceRepo.findByOiOrderNum(meList.get(j).getOiOrderNum());
                  // 옵션 가격이 존재한다면, 옵션을 선택한 것이므로
                  if (price.getTotalOptionPrice() != null) {
                    // map에 가격정보 저장
                    map3.put("price", price.getTotalMenuPrice() + price.getTotalOptionPrice());
                  } else {
                    map3.put("price", price.getTotalMenuPrice());
                  }
                  // 리스트에 map정보를 저장
                  orderlist.add(map3);
                }
              }
              // 다시 반복문 시작으로 돌아가서
              // 두번째 mlist의 값의 주문번호에 따른 리스트 값을 melist에 저장
              // 저장된 정보의 주문번호가 이미 저장된 주문 번호가 아니라면, 주문 번호가 같은 주문 메뉴와 옵션리스트를 저장할 리스트를 새로 생성
              // 위 과정을 반복함
            }
            map2.put("list", orderlist);
          }
          else {
            map2.put("status", false);
            map2.put("message", "주문내역 없음");
          }
        }
      }
      return map2;
    }
    return map;
  }
  

  // 멤버별 간단한 주문 내역 출력
  public Map<String, Object> showBriefOrderList( HttpSession session, @PageableDefault (size = 8) Pageable page) {
    Map<String, Object> map = new LinkedHashMap<>();
    MbMemberInfoEntity member = (MbMemberInfoEntity) session.getAttribute("loginUser");
    if (member == null) {
      map.put("status", false);
      map.put("message", "로그인 후 이용하실 수 있습니다.");
    } else {
      Map<String, Object> map2 = new LinkedHashMap<>();
      // 멤버로 주문 내역 찾아서 메뉴 출력시, 주문 번호 별로 묶어야 함
      // 메뉴 옵션은 메뉴 주문 번호 별로 출력 해야함 

      // map을 저장할 수 있는 list 생성
      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

      // 멤버 변수를 통해 멤버가 주문한 메뉴 정보를 List에 저장
      List<MpMypageMenuChoiceEntity> mList = menuChoiceRepo.findByMiSeq(member.getMiSeq());

      if (mList.isEmpty()) {
        map2.put("status", false);
        map2.put("message", "주문내역 없음");
      } 
      else {
        // 반복문을 통해 멤버가 주문한 메뉴 정보 mList에서 주문 번호를 가져와서 
        for (int m = 0; m < mList.size(); m++) {
          // 만약 리스트의 상태값이 배송완료된 상태라면
          if (mList.get(m).getOiStatus() == 2) {
            // 주문번호를 통해 주문한 메뉴정보를 조회해서 meList에 다시 저장
            List<MpMypageMenuChoiceEntity> meList = menuChoiceRepo.findByOiOrderNum(mList.get(m).getOiOrderNum());

            // 임의의 변수를 지정
            int count = 0;
            // 반복문을 통해, 리스트 안에 저장된 값의 주문번호가 새로 저장될 값의 주문번호가 일치하는 경우가 있다면,
            // 변수의 값을 1높이고 반복문을 멈추게 설정
            for (int i = 0; i < m; i++) {
              if (mList.get(i).getOiOrderNum().equals(mList.get(m).getOiOrderNum())) {
                count++;
                break;
              }
            }
            // 반복문을 다 돌고 나온 후, 만약 변수의 값이 1이 아니라면, 같은 주문번호의 주문이 없는 것이므로 
            // map 에 저장
            if (count != 1) {
              for (int j = 0; j < meList.size(); j++) {
                // 주문번호별 주문 리스트의 마지막 메뉴 정보를 대표로 들고 오기위해, 임의의 변수에 주문 리스트의 마지막 index값을 준다.
                if (j == meList.size() - 1) {
                  Map<String, Object> map3 = new LinkedHashMap<>();
                  // 주문 번호 별 가게 이름, 주문메뉴, 메뉴 수량, 주문 일자, 주문 금액
                  MpMenuCategoryEntity menuCate = menuCateRepo.findByMcSeq(meList.get(j).getMcSeq());
                  MpStoreInfoEntity store = storeRepo.findBySiSeq(menuCate.getStore().getSiSeq());
                  MpOrderInfoEntity order = oRepo.findByOiSeq(meList.get(j).getOiSeq());
                  // 주문 번호 map에 저장
                  map3.put("orderNum", order.getOiOrderNum());
                  // 가게 이름 map에 저장
                  map3.put("storeName", store.getSiName());
                  // 주문한 메뉴 중 대표 1개만 map에 저장
                  map3.put("menuName", meList.get(j).getMniName());
                  // 주문한 메쥬 종류 수 
                  map3.put("menuTotal", meList.size());
                  // 주문일자, 배달완료일자 저장
                  map3.put("orderDate", order.getOiOrderDt());
                  map3.put("finishDate", order.getOiFinishDt());
                  // 주문 가격 저장
                  MpMypageOrderPriceByOrderNumEntity price = priceRepo.findByOiOrderNum(meList.get(j).getOiOrderNum());
                  if (price.getTotalOptionPrice() != null) {
                    map3.put("price", price.getTotalMenuPrice() + price.getTotalOptionPrice());
                  } else {
                    map3.put("price", price.getTotalMenuPrice());
                  }
                  list.add(map3);
                }
              }
            }
            map2.put("list", list);
          }
          else {
            map2.put("status", false);
            map2.put("message", "주문내역 없음");
          }
        }
        // 다시 반복문 시작으로 돌아가서
        // 두번째 mlist의 값의 주문번호에 따른 리스트 값을 melist에 저장
        // 저장된 정보의 주문번호가 이미 저장된 주문 번호가 아니라면, 주문 번호가 같은 주문 메뉴와 옵션리스트를 저장할 리스트를 새로 생성
        // 위 과정을 반복함
      }
      return map2;
    }
    return map;
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
        MpOrderInfoVO vo = new MpOrderInfoVO();
        if (option.isEmpty()) {
          vo = MpOrderInfoVO.builder()
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
          vo = MpOrderInfoVO.builder()
          .oiSeq(mlist.get(i).getOiSeq())
          .oiOrderNum(mlist.get(i).getOiOrderNum())
          .miSeq(mlist.get(i).getMiSeq())
          .mniName(mlist.get(i).getMniName())
          .menuAmount(mlist.get(i).getMenuAmount())
          .menuPrice(mlist.get(i).getMenuPrice())
          .mcSeq(mlist.get(i).getMcSeq())
          .oiStatus(mlist.get(i).getOiStatus())
          .optionList(optionChoiceRepo.findByOiSeq(mlist.get(i).getOiSeq()))
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


  // 주문 번호를 통해 주문표 출력
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
          
          // 첫번째 주문의 oiSeq를 통해 옵션을 찾아서 리스트에 저장하고
          List<MpMypageOptionChoiceEntity> option = optionChoiceRepo.findByOiSeq(mlist.get(m).getOiSeq());
          // 첫번째 주문의 oiSeq를 통해 가격정보를 찾아서 변수에 저장한다
          MpMypageOrderPriceByOiSeqEntity price = priceOiSeqRepo.findByOiSeq(mlist.get(m).getOiSeq());
          MpWishListVO wish = new MpWishListVO();
          // 만약 옵션리스트가 비어있다면 옵션이 선택되지 않았으므로, 
          // 옵션은 null로 저장하고, 가격도 메뉴 가격만 저장한다
          if (option.isEmpty()) {
            wish = MpWishListVO.builder().oiSeq(mlist.get(m).getOiSeq())
            .oiOrderNum(mlist.get(m).getOiOrderNum())
            .mniName(mlist.get(m).getMniName())
            .menuPrice(mlist.get(m).getMenuPrice())
            .menuAmount(mlist.get(m).getMenuAmount())
            .option(null)
            .menuOrderPrice(price.getOiSeqPrice()).build(); 
          }
          // 만약 옵션리스트가 있다면 저장하고
          // 가격은 메뉴와 옵션의 가격이 더해진 가격을 저장한다.
          else {
            wish = MpWishListVO.builder().oiSeq(mlist.get(m).getOiSeq())
            .oiOrderNum(mlist.get(m).getOiOrderNum())
            .mniName(mlist.get(m).getMniName())
            .menuPrice(mlist.get(m).getMenuPrice())
            .menuAmount(mlist.get(m).getMenuAmount())
            .option(optionChoiceRepo.findByOiSeq(mlist.get(m).getOiSeq()))
            .menuOrderPrice(price.getOiSeqPrice()).build(); 
          }
          // 리스트에 저장한다.
          list.add(wish);
          // 합계 금액을 출력
          totalPrice += price.getOiSeqPrice();
        }
        // 만약 list가 비어있다면, 장바구니상태인 주문 내역이 없는 것이므로 
        if (list.isEmpty()) {
          Map<String, Object> map3 = new LinkedHashMap<String, Object>();
          map3.put("status", false);
          map3.put("message", "주문표가 비어있습니다.");
          return map3;
        }
        // map에 주문정보를 저장한다.
        map2.put("menu", list);
      }
      map2.put("totalPrice", totalPrice);
      // 주문메뉴와 옵션이 저장된 리스트 map 에 저장
      return map2;
      }    
    return map;
  }
  
}
