package com.greenart.yogio.chacha.vo.owner;

import lombok.Data;

@Data
public class OwnerUpdateVO {
    private Long seq;
    private String pwd;
    private String nickname;
    private String phone;
    private String email;
}
