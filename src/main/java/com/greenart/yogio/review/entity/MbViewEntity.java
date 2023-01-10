package com.greenart.yogio.review.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "view_review")
public class MbViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_seq") private Long reSeq;
    @Column(name = "re_reg_dt") private LocalDate reRegDt;
    @Column(name = "re_score") private Integer reScore;
    @Column(name = "re_content") private String reContent;
    @Column(name = "re_oi_seq") private Long reOiSeq;
    @Column(name = "re_taste_score") private Integer reTasteScore;
    @Column(name = "re_quantity_score") private Integer reQuantityScore;
    @Column(name = "re_delivery_score") private Integer reDeliveryScore;

    @Column(name = "ri_seq") private Long riSeq; 
    @Column(name = "ri_name") private String riName; 
    @Column(name = "ri_order") private Integer riOrder; 
    @Column(name = "ri_re_seq") private Long riReSeq; 
}
