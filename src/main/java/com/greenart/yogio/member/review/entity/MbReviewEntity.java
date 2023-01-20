package com.greenart.yogio.member.review.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review_info")
@Builder
public class MbReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_seq") private Long reSeq;
    @Column(name = "re_reg_dt") private Date reRegDt;
    @Column(name = "re_score") private Integer reScore;
    @Column(name = "re_content") private String reContent;
    @Column(name = "re_oi_seq") private Long reOiSeq;
    @Column(name = "re_taste_score") private Integer reTasteScore;
    @Column(name = "re_quantity_score") private Integer reQuantityScore;
    @Column(name = "re_delivery_score") private Integer reDeliveryScore;

    // @ManyToOne
    // @JoinColumn(name = "re_oi_seq") MbOrderInfoEntity reOiSeq;
}
