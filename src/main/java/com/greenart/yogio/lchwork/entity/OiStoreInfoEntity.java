package com.greenart.yogio.lchwork.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_info")
@DynamicInsert
@Builder
public class OiStoreInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq") private Long siSeq;
    @Column(name = "si_name") private String siName;
    @Column(name = "si_uri") private String siUri;
    @Column(name = "si_min_order_price") private Integer siMinOrderPrice;
    @Column(name = "si_discount_price") private Integer siDiscountPrice;
    @Column(name = "si_discount_condition") private String siDiscountCondition;
    @Column(name = "si_di_seq") private Long siDiSeq;
    @Column(name = "si_clean_info") private Integer siCleanInfo;
    @Column(name = "si_file_name") private String siFileName;
}
