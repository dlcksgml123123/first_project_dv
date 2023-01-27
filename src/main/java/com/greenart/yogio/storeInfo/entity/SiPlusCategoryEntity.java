package com.greenart.yogio.storeInfo.entity;

import org.hibernate.annotations.ColumnDefault;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="plus_category")
@DynamicInsert
public class SiPlusCategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pc_seq") private Long pcSeq;
    @Column(name="pc_name") private String pcName;
    @Column(name="pc_multi_choice")  @ColumnDefault("0") private Integer pcMultiChoice;
    @Column(name="pc_essential_choice") @ColumnDefault("1") private Integer pcEssentialChoice;
    
}
