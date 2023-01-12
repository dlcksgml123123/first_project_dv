package com.greenart.yogio.mypage.member.vo;

import com.greenart.yogio.mypage.member.entity.MpMemberInfoEntity;

import lombok.Data;

@Data
public class MpLoginUserDetailVO {
  private String id;
  private String nickname;
  private String address;
  private String email;
  private String phone;

  public MpLoginUserDetailVO(MpMemberInfoEntity entity) {
    this.id = entity.getMiId();
    this.nickname = entity.getMiNickname();
    this.address = entity.getMiAddress();
    this.email = entity.getMiEmail();
    this.phone = entity.getMiPhone();
  }
}