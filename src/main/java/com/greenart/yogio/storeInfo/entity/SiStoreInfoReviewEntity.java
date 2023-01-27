package com.greenart.yogio.storeInfo.entity;

import java.util.Date;

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
@Table(name = "store_info_review")
public class SiStoreInfoReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_seq")                private Long reSeq;
    @Column(name = "si_seq")                private Long siSeq;
    @Column(name = "mi_nickname")           private String miNickname;
    @Column(name = "re_reg_dt")             private Date reRegDt;
    @Column(name = "mni_name")              private String mniName;
    @Column(name = "re_taste_score")        private Integer reTasteScore;
    @Column(name = "re_quantity_score")     private Integer reQuantityScore;
    @Column(name = "re_delivery_score")     private Integer reDeliveryScore;
    @Column(name = "re_score")              private Integer reScore;
    @Column(name = "ri_name")               private String riName;
    @Column(name = "re_content")            private String reContent;
    @Column(name = "owi_nickname")          private String owiNickname;
    @Column(name = "ro_content")            private String roContent;
    
    // @Column(name = "total") private Integer Total;
    // @Column(name = "avg") private Double Avg;
}
