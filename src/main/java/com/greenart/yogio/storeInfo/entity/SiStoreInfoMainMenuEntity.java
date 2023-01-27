package com.greenart.yogio.storeInfo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="store_info_mainmenu")
public class SiStoreInfoMainMenuEntity {
    @Id
    @Column(name="mni_seq") private Long mniSeq;
    @Column(name="si_seq") private Long siSeq;
    @Column(name="mc_name") private String mcName;
    @Column(name="mc_explanation") private String mcExplanation;
    @Column(name="mni_img") private String mniImg;
    @Column(name="mni_name") private String mniName;
    @Column(name="mni_discount") private Double mniDiscount;
    @Column(name="mni_price") private Integer mniPrice;
    
}
