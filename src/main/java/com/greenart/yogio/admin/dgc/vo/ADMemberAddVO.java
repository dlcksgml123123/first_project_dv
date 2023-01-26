package com.greenart.yogio.admin.dgc.vo;

import lombok.Data;

@Data
public class ADMemberAddVO {
  private String  id;
  private String  pwd;
  private String  email;
  private String  phone;
  private String  nickname;
  private String  address;
  private Integer status;
}
