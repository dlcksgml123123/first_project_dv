package com.greenart.yogio.lchwork.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "view8")
public class OiPaymentEndEntity {
    @Id
    @Column(name = "mi_seq") private Long miSeq;
    @Column(name = "oi_order_num") private String oiOrderNum;
    @Column(name = "si_name") private String siName;
    @Column(name = "real_total_price") private Integer totalPrice; 
}
