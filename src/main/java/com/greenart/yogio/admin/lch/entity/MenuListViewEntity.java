package com.greenart.yogio.admin.lch.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Immutable
@Table(name = "menu_list_view")
@DynamicInsert
@Builder
public class MenuListViewEntity {
    @Id
    @Column(name = "mni_seq")        private Long mniSeq;
    @Column(name = "si_seq")       private Long siSeq;
    @Column(name = "mni_name")      private String mniName;
    @Column(name = "mni_price")     private Integer mniPrice;
    @Column(name = "mni_discount")  private Double mniDiscount;
}
