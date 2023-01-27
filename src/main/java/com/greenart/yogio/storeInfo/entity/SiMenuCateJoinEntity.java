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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "menu_cate_join")
public class SiMenuCateJoinEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "mcj_seq") private Long mcjSeq;
    // @Column(name= "mcj_mc_seq") private Long mcjMcSeq;
    // @Column(name= "mcj_mni_seq") private Long mcjMniSeq;
    @OneToOne @JoinColumn(name="mcj_mc_seq") SiMenuCategoryEntity mcategory;
    @OneToOne @JoinColumn(name="mcj_mni_seq") SiMenuInfoEntity menu;
}
