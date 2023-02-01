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
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Builder
@Table(name = "menu_plus_join")
public class MenuplusjoinEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mpj_seq") private Long mpjSeq;
    @Column(name="mpj_mcj_seq") private Long mpjMcjSeq;
    @Column(name="mpj_pcj_seq") private Long mpjPcjSeq;
}
