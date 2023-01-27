package com.greenart.yogio.storeInfo.entity;

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
@Table(name="plus_menu")
public class SiPlusMenuEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "pm_seq") private Long pmSeq;
    @Column(name= "pm_name") private String pmName;
    @Column(name= "pm_price") private Integer pmPrice;
}
