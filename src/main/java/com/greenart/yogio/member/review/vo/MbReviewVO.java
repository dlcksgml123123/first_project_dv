package com.greenart.yogio.member.review.vo;

import java.time.LocalDate;
import java.util.Date;

import com.greenart.yogio.member.review.entity.MbOrderInfoEntity;
import com.greenart.yogio.member.review.entity.MbReviewEntity;
import com.greenart.yogio.member.review.entity.MbReviewImageEntity;

import lombok.Data;

@Data
public class MbReviewVO {
  private Date regDt;         //regDt;
  private Integer   score;         //reScore;
  private String    content;       //reContent;
  private String      oiSeq;         //reOiSeq;
  private Integer   tasteScore;    //reTasteScore;
  private Integer   quantityScore; //reQuantityScore;
  private Integer   deliveryScore; //reDeliveryScore;
  private String    name;          //riName; image
  private Integer   order;         //riOrder; image
  public MbReviewVO(MbReviewEntity rentity, MbReviewImageEntity ientity) {
    this.regDt = new Date();
    this.score = rentity.getReScore();
    this.content = rentity.getReContent();
    this.tasteScore = rentity.getReTasteScore();
    this.quantityScore = rentity.getReQuantityScore();
    this.deliveryScore = rentity.getReDeliveryScore();
    this.name = ientity.getRiName();
    this.order = ientity.getRiOrder();
  }
  public MbReviewVO() {

  }
}
