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
@Table(name = "menu_cate_join")
@Entity
public class SsMenucateJoinEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcj_seq") private Long mcjSeq;
    @ManyToOne @JoinColumn(name = "mcj_mc_seq") SsStoreMenusearchEntity mcjMcSeq;
    @ManyToOne @JoinColumn(name = "mcj_mni_seq") SsMenuInfoEntity mcjMniSeq;
    
}
