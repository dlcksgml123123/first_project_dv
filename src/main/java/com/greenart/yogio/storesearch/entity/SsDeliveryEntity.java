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
@Table(name = "delivery_info")
@Entity
public class SsDeliveryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "di_seq") private Long diSeq;
    @Column(name = "di_distance") private Integer diDistance;
    @Column(name = "di_delivery_price") private Integer diDeliveryPrice;
    @Column(name = "di_time") private String diTime;
}
