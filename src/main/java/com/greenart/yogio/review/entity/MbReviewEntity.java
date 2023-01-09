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
@Table(name = "review_info")
public class MbReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_seq") private Long reSeq;
    @Column(name = "re_reg_dt") private LocalDate reRegDt;
    @Column(name = "re_score") private Integer reScore;
    @Column(name = "re_content") private String reContent;
    @Column(name = "re_oi_seq") private Long reOiSeq;
    @Column(name = "re_taste_score") private Integer reTasteScore;
    @Column(name = "re_quantity_score") private Integer reQuantityScore;
    @Column(name = "re_delivery_score") private Integer reDeliveryScore;
}
