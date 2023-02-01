package com.greenart.yogio.member.review.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_info")
@Builder
public class MbOrderInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oi_seq") private Long oiSeq;
    @Column(name = "oi_mni_seq") private Long oiMniSeq;
    @Column(name = "oi_mi_seq") private Long oiMiSeq;
    @Column(name = "oi_menu_amount") private Integer oiMenuAmount;
    @Column(name = "oi_status") private Integer oiStatus;
    @Column(name = "oi_order_num") private String oiOrderNum;
    @Column(name = "oi_order_dt") private Date oiOrderDt;
    @Column(name = "oi_finish_dt") private Date oiFinishDt;
}
