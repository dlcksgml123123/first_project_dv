package com.greenart.yogio.storeInfo.entity;

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
@Table(name="plus_cate_join")
public class SiPlusCateJoinEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcj_seq") private Long pcjSeq;
    // @Column(name = "pcj_pc_seq") private Long pcjPcSeq;
    // @Column(name = "pcj_pm_seq") private Long pcjPmSeq;
    @OneToOne @JoinColumn (name="pcj_pc_seq") SiPlusCategoryEntity pcate;
    @OneToOne @JoinColumn (name="pcj_pm_seq") SiPlusMenuEntity pmenu;
    
}
