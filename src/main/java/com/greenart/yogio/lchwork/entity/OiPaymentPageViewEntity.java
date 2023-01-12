package com.greenart.yogio.lchwork.entity;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.Immutable;

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
@Immutable
@Table(name = "payment_page_view")
@DynamicInsert
@Builder
public class OiPaymentPageViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oi_seq")            private Long oiSeq; 
    @Column(name = "mi_seq")            private Long miSeq;
    @Column(name = "mi_address")        private String miAddress;
    @Column(name = "mi_phone")          private String miPhone;
    @Column(name = "si_name")           private String siName;
    @Column(name = "di_delivery_price") private Integer diDeliveryPrice;
    @Column(name = "mni_name")          private String mniName;
    @Column(name = "mni_price")         private Integer mniPrice;
    @Column(name = "oi_menu_amount")    private Integer oiMenuAmount;
    @Column(name = "pm_name")           private String pmName;
    @Column(name = "pm_price")          private Integer pmPrice;
    @Column(name = "pmc_amount")        private Integer pmcAmount;
    @Column(name = "menu_total_price")  private Integer menuTotalPrice;
    @Column(name = "plus_total_price")  private Integer plusTotalPrice;
    @Column(name = "total_price")       private Integer totalPrice;
}
