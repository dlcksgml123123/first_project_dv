package com.greenart.yogio.storeInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="store_info_plusmenu")
public class SiStoreInfoPlusMenuEntity {
    @Id
    @Column(name = "pm_seq") @JsonIgnore
    private Long pmSeq;
    @Column(name="mni_seq") private Long mniSeq;
    @Column(name="pc_name") private String pcName;
    @Column(name="pc_multi_choice") private Integer pcMultiChoice;
    @Column(name="pc_essential_choice") private Integer pcEssentialChoice;
    @Column(name="pm_name") private String pmName;
    @Column(name="pm_price") private Integer pmPrice;
    
}