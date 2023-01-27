package com.greenart.yogio.storeInfo.vo;

import lombok.Data;

@Data
public class SiStoreInfoVO {
   private String  siName;
   private String  siUri;
   private Integer siMinOrderPrice;
   private Integer siDiscountPrice;
   private String  siDiscountCondition;
   private Long    siDiSeq;
   private Integer siCleanInfo;
   private String  siFileName;
   private Integer diDistance;
   private Integer diDeliveryPrice;
   private String  diTime;

    
}
