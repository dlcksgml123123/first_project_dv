package com.greenart.yogio.storesearch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store_cate_join")
@Entity
public class SsStoreCateJoinEntoty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scs_seq") private Long scjSeq;
    @ManyToOne @JoinColumn(name = "scs_sc_seq") SsCategoryList scjScSeq;
    @ManyToOne @JoinColumn(name = "scs_si_seq") SsStoresearchEntity scjSiSeq;

    
}
