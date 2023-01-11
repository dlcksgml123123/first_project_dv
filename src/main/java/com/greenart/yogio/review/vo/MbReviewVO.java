package com.greenart.yogio.review.vo;

import java.time.LocalDate;

import com.greenart.yogio.review.entity.MbReviewEntity;
import com.greenart.yogio.review.entity.MbReviewImageEntity;

import lombok.Data;

@Data
public class MbReviewVO {
  private LocalDate regDt;         //regDt;
  private Integer   score;         //reScore;
  private String    content;       //reContent;
  private Long      oiSeq;         //reOiSeq;
  private Integer   tasteScore;    //reTasteScore;
  private Integer   quantityScore; //reQuantityScore;
  private Integer   deliveryScore; //reDeliveryScore;
  private Long      reSeq;         //riReSeq; image
  private String    name;          //riName; image
  private Integer   order;         //riOrder; image
  public MbReviewVO(MbReviewEntity rentity, MbReviewImageEntity ientity) {
    this.regDt = rentity.getReRegDt();
    this.score = rentity.getReScore();
    this.content = rentity.getReContent();
    this.oiSeq = rentity.getReOiSeq();
    this.tasteScore = rentity.getReTasteScore();
    this.quantityScore = rentity.getReQuantityScore();
    this.deliveryScore = rentity.getReDeliveryScore();
    // this.reSeq = ientity.getRiReSeq();
    this.name = ientity.getRiName();
    this.order = ientity.getRiOrder();
  }
  public MbReviewVO() {

  }
}
