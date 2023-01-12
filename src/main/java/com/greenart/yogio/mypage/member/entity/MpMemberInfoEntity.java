package com.greenart.yogio.mypage.member.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "member_info")
@Builder
public class MpMemberInfoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mi_seq")        private Long miSeq;
  @Column(name = "mi_id")         private String miId;
  @Column(name = "mi_pwd")        private String miPwd;
  @Column(name = "mi_email")      private String miEmail;
  @Column(name = "mi_phone")      private String miPhone;
  @Column(name = "mi_nickname")   private String miNickname;
  @Column(name = "mi_address")    private String miAddress;
  @Column(name = "mi_status")     @ColumnDefault("0") private Integer  miStatus;
}
