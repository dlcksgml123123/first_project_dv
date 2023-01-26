package com.greenart.yogio.mypage.order.vo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenart.yogio.mypage.order.entity.MpMypageMenuChoiceEntity;
import com.greenart.yogio.mypage.order.entity.MpMypageOptionChoiceEntity;
import com.greenart.yogio.mypage.order.repository.MpMypageOptionChoiceRepository;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MpOrderInfoVO {
  private Long oiSeq;
  private String oiOrderNum;
  private Long miSeq;
  private String mniName;
  private Integer menuAmount;
  private Integer menuPrice;
  private Long mcSeq;
  private Integer oiStatus;
  private List<MpMypageOptionChoiceEntity> optionList;
  
  public MpOrderInfoVO(MpMypageMenuChoiceEntity menu) {
    this.oiSeq = menu.getOiSeq(); 
    this.oiOrderNum = menu.getOiOrderNum(); 
    this.miSeq = menu.getMiSeq();
    this.mniName = menu.getOiOrderNum();
    this.menuAmount = menu.getMenuAmount();
    this.menuPrice = menu.getMenuPrice();
    this.mcSeq = menu.getMcSeq(); 
    this.oiStatus = menu.getOiStatus(); 
  }
}
