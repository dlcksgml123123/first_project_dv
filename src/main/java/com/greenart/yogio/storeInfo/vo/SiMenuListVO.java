package com.greenart.yogio.storeInfo.vo;


import java.util.List;

import com.greenart.yogio.storeInfo.entity.SiMenuPlusJoinEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreInfoPlusMenuEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiMenuListVO {
    // private Long mpjSeq;
    private Long siSeq;
    private Long mniSeq;
    private String mcName;
    private String mcExplanation;
    private String mniImg;
    private String mniName;
    private Double mniDisCount;
    private Integer mniPrice;
    private List<SiStoreInfoPlusMenuEntity> plusmenu;
    // private String pcName;
    // private Integer pcMultiChoice;
    // private Integer pcEssentialChoice;
    // private String pmName;
    // private Integer pmPrice;

    public SiMenuListVO(SiMenuPlusJoinEntity entity) {
            // this.mpjSeq=entity.getMpjSeq();
            this.siSeq= entity.getMenuj().getMcategory().getStore().getSiSeq();
            this.mniSeq=entity.getMenuj().getMenu().getMniSeq();
            this.mcName=entity.getMenuj().getMcategory().getMcName();
            this.mcExplanation=entity.getMenuj().getMcategory().getMcExplanation();
            this.mniImg= entity.getMenuj().getMenu().getMniImg();
            this.mniName= entity.getMenuj().getMenu().getMniName();
            this.mniDisCount=entity.getMenuj().getMenu().getMniDiscount();
            this.mniPrice=entity.getMenuj().getMenu().getMniPrice();
            // this.pcName=entity.getPmenuj().getPcate().getPcName();
            // this.pcMultiChoice= entity.getPmenuj().getPcate().getPcMultiChoice();
            // this.pcEssentialChoice= entity.getPmenuj().getPcate().getPcEssentialChoice();
            // this.pmName= entity.getPmenuj().getPmenu().getPmName();
            // this.pmPrice= entity.getPmenuj().getPmenu().getPmPrice();
           
    }

}
