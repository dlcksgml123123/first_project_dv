package com.greenart.yogio.mypage.order.entity;

import java.util.Date;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.mypage.store.entity.MpMenuInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_info")
public class MpOrderInfoEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "oi_seq")
  private Long oiSeq;
  @ManyToOne @JoinColumn(name = "oi_mni_seq") MpMenuInfoEntity menu;
  // @Column(name = "oi_mni_seq") private Long oiMniSeq;
  @ManyToOne @JoinColumn(name = "oi_mi_seq") MbMemberInfoEntity member;  
  // @Column(name = "oi_mi_seq")             private Long oiMiSeq;  
  @Column(name = "oi_menu_amount")        private Integer oiMenuAmount;  
  @Column(name = "oi_status")             private Integer oiStatus;  
  @Column(name = "oi_order_num")          private Integer oiOrderNum;  
  @Column(name = "oi_order_dt")           private Date oiOrderDt;  
  @Column(name = "oi_finish_dt")          private Date oiFinishDt;  
}
