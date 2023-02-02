package com.greenart.yogio.member.vo;

import lombok.Data;

@Data
public class MbMemberVO {
  private String miId;
  private String miPwd;
  private String miEmail;
  private String miPhone;
  private String miNickname;
  private String miAddress;
  private Integer miStatus;
}
