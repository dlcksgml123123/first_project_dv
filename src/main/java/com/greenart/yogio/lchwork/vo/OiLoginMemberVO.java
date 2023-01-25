package com.greenart.yogio.lchwork.vo;

import com.greenart.yogio.lchwork.entity.OiMemberInfoEntity;
import com.greenart.yogio.lchwork.entity.OiStoreInfoEntity;

import lombok.Data;

@Data
public class OiLoginMemberVO {
    private String miPhone;
    private String miAddress;
    private String siName;

    public OiLoginMemberVO(OiMemberInfoEntity entity) {
        this.miPhone = entity.getMiPhone();
        this.miAddress = entity.getMiAddress();
    }
    public OiLoginMemberVO(OiStoreInfoEntity entity) {
        this.siName = entity.getSiName();
    }
}
