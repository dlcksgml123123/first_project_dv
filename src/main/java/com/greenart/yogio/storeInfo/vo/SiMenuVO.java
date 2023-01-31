package com.greenart.yogio.storeInfo.vo;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoMainMenuEntity;

import lombok.Data;

@Data
public class SiMenuVO {
    private Long siSeq;
    private Long mniSeq;
    private String mniImg;
    private String mniName;
    private Integer mniPrice;

public void copyValues(SiStoreInfoMainMenuEntity data) {
this.siSeq = data.getSiSeq();
this.mniSeq = data.getMniSeq();
this.mniImg = data.getMniImg();
this.mniName = data.getMniName();
this.mniPrice = data.getMniPrice();
}

}
