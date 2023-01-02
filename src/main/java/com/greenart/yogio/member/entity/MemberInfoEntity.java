package com.greenart.yogio.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member_info")
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mi_seq") private Long miSeq ;
    @Column(name = "mi_id") private String miId;
    @Column(name = "mi_pwd") private String miPwd;
    @Column(name = "mi_email") private String miEmail;
    @Column(name = "mi_phone") private String miPhone;
    @Column(name = "mi_nickname") private String miNickname;
    @Column(name = "mi_address") private String miAddress;
    @Column(name = "mi_status") private Integer miStatus;
}
