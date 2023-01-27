package com.greenart.yogio.storeInfo.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="menu_plus_join")
public class SiMenuPlusJoinEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mpj_seq") private Long mpjSeq;
    // @Column(name="mpj_mni_seq") private Long mpjMniSeq;
    // @Column(name="mpj_pm_seq") private Long mpjPmSeq;
    @OneToOne @JoinColumn(name = "mpj_mcj_seq") SiMenuCateJoinEntity menuj;
    @OneToOne @JoinColumn(name = "mpj_pcj_seq") SiPlusCateJoinEntity pmenuj;
    
}
