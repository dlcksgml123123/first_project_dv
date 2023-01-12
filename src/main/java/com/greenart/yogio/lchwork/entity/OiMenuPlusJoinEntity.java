package com.greenart.yogio.lchwork.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu_plus_join")
@DynamicInsert
@Builder
public class OiMenuPlusJoinEntity {
    @Column(name = "mpj_seq") private Long mpjSeq;
    @Column(name = "mpj_mni_seq") private Long mpjMniSeq;
    @Column(name = "mpj_pm_seq") private Long mpjPmSeq;
}
