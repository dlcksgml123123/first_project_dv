package com.greenart.yogio.storeInfo.vo;


import com.greenart.yogio.storeInfo.entity.SiMenuPlusJoinEntity;


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

    public SiMenuListVO(SiMenuPlusJoinEntity entity) {
            this.mpjSeq=entity.getMpjSeq();
            this.siSeq= entity.getMenuj().getMcategory().getStore().getSiSeq();
            this.mniSeq=entity.getMenuj().getMenu().getMniSeq();
            this.mcName=entity.getMenuj().getMcategory().getMcName();
            this.mcExplanation=entity.getMenuj().getMcategory().getMcExplanation();
            this.mniImg= entity.getMenuj().getMenu().getMniImg();
            this.mniName= entity.getMenuj().getMenu().getMniName();
            this.mniDisCount=entity.getMenuj().getMenu().getMniDiscount();
            this.mniPrice=entity.getMenuj().getMenu().getMniPrice();
            this.pcName=entity.getPmenuj().getPcate().getPcName();
            this.pcMultiChoice= entity.getPmenuj().getPcate().getPcMultiChoice();
            this.pcEssentialChoice= entity.getPmenuj().getPcate().getPcEssentialChoice();
            this.pmName= entity.getPmenuj().getPmenu().getPmName();
            this.pmPrice= entity.getPmenuj().getPmenu().getPmPrice();
           
    }

}
