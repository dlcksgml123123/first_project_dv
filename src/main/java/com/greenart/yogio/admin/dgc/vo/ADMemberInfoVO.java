package com.greenart.yogio.admin.dgc.vo;

import lombok.Data;

@Data
public class ADMemberInfoVO {
  private String  member_id;
  private String  member_pwd;
  private String  member_email;
  private String  member_phone;
  private String  member_nickname;
  private String  member_address;
  private Integer member_status;
}
