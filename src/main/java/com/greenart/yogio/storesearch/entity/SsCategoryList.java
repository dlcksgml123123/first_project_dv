package com.greenart.yogio.storesearch.entity;

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
@Table(name = "store_category")
@Entity
public class SsCategoryList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sc_seq") private Long scSeq;
    @Column(name = "sc_name") private String scName;
    
}
