package com.greenart.yogio.storeInfo.vo;

import java.util.List;

import com.greenart.yogio.storeInfo.entity.SiMenuCategoryEntity;
import com.greenart.yogio.storeInfo.entity.SiMenuInfoEntity;
import com.greenart.yogio.storeInfo.entity.SiMenuPlusJoinEntity;
import com.greenart.yogio.storeInfo.entity.SiPlusCategoryEntity;
import com.greenart.yogio.storeInfo.entity.SiPlusMenuEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreInfoEntity;

import lombok.Data;
@Data
public class SiMenuListVO {
    private Long mpjSeq;
    private Long siSeq;
    private Long mniSeq;
    private String mcName;
    private String mcExplanation;
    private String mniImg;
    private String mniName;
    private Double mniDisCount;
    private Integer mniPrice;
    private String pcName;
    private Integer pcMultiChoice;
    private Integer pcEssentialChoice;
    private String pmName;
    private Integer pmPrice;

    public SiMenuListVO (SiStoreInfoEntity storeentity, SiMenuInfoEntity menuentity, SiMenuCategoryEntity mcateentity,
    SiPlusCategoryEntity plusentity, SiPlusMenuEntity pmenuentity, SiMenuPlusJoinEntity mpjentity) {
        this.mpjSeq=mpjentity.getMpjSeq();
        this.siSeq= storeentity.getSiSeq();
        this.mniSeq= menuentity.getMniSeq();
        this.mcName= mcateentity.getMcName();
        this.mcExplanation= mcateentity.getMcExplanation();
        this.mniImg= menuentity.getMniImg();
        this.mniName= menuentity.getMniName();
        this.mniDisCount= menuentity.getMniDiscount();
        this.mniPrice= menuentity.getMniPrice();
        this.pcName= plusentity.getPcName();
        this.pcMultiChoice= plusentity.getPcMultiChoice();
        this.pcEssentialChoice= plusentity.getPcEssentialChoice();
        this.pmName= pmenuentity.getPmName();
        this.pmPrice= pmenuentity.getPmPrice();
    
}


   
  

}
