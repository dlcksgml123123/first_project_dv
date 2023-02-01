package com.greenart.yogio.admin.sang.entity;

import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="menu_cate_join")
@Builder
public class MenucateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcj_seq") private Long mcjSeq;
    @Column(name = "mcj_mc_seq") private Long mcjMcSeq;
    @Column(name = "mcj_mni_seq") private Long mcjMniSeq;
    
}
