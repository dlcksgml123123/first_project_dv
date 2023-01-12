package com.greenart.yogio.lchwork.entity;

import org.hibernate.annotations.DynamicInsert;

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
@Table(name = "plus_menu_choice")
@DynamicInsert
@Builder
public class OiPlusMenuChoiceEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pmc_seq") private Long pmcSeq;
    @Column(name = "pmc_pm_seq") private Long pmcPmSeq;
    @Column(name = "pmc_amount") private Integer pmcAmount;
    @Column(name = "pmc_oi_seq") private Long pmcOiSeq; // 추가
}   
