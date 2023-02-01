package com.greenart.yogio.admin.sang.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plus_menu")
@Builder
public class PlusMenuJoinEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pm_seq") private Long pmSeq;
    @Column(name = "pm_name") private String pmName;
    @Column(name = "pm_price") private Integer pmPrice;
}
