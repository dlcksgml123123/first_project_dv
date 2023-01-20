package com.greenart.yogio.storeInfo.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_category")
@DynamicInsert
public class SiMenuCategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mc_seq") private Long mcSeq;
    // @OneToOne @JoinColumn(name ="mc_seq") SiMenuCateJoinEntity mcate;
    @Column(name="mc_name") private String mcName;
    @Column(name="mc_explanation") private String mcExplanation;
    // @Column(name="mc_si_seq") private Long mcSiSeq;
    @ManyToOne @JoinColumn(name="mc_si_seq") SiStoreInfoEntity store;
}
