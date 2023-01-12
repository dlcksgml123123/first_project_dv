package com.greenart.yogio.storesearch.entity;

import org.hibernate.annotations.Immutable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table (name = "store_search_view")
public class SsStoreSearchViewEntity {
    @Id 
    @Column(name = "si_seq")private  Long siSeq;
    @Column(name = "si_name") private String siName;
    @Column(name = "si_clean_info") private Integer siCleanInfo;
    @Column(name = "si_uri") private String siUri;
    @Column(name = "si_min_order_price") private Integer siMinOrderPrice;
    @Column(name = "di_time") private String diTime;
    @Column(name = "sl_status") private Integer slStatus;
    @Column(name = "mi_seq") private Long miSeq;
    @Column(name = "reviewcnt") private Integer reviewcnt;
    @Column(name = "average") private Double average;
    @Column(name = "owner_review_cnt") private Integer ownerReviewCnt;
    @Column(name = "sc_seq") private Long scSeq;
    @Column(name = "sc_name") private String scName;
    

}
