package com.greenart.yogio.storesearch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_category")
@Entity
public class SsStoreMenusearchEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mc_seq") private Long mcSeq;
    @Column(name = "mc_name") private String mcName;
    @Column(name = "mc_explanation") private String mcExplanation;
    @ManyToOne @JoinColumn(name = "mc_si_seq") SsStoresearchEntity mcSiSeq;

}
