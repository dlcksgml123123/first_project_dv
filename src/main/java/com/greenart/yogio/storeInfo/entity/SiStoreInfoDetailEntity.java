package com.greenart.yogio.storeInfo.entity;

import org.hibernate.annotations.Immutable;

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
@Immutable
@Table(name="store_info_detail")
public class SiStoreInfoDetailEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="si_seq") private Long siSeq;
    @Column(name="oni_img_path") private String oniImgPath;
    @Column(name="sdi_owner_notice") private String sdiOwnerNotice;
    @Column(name="sdi_open_close") private String sdiOpenClose;
    @Column(name="sdi_phone") private String sdiPhone;
    @Column(name="sdi_adress") private String sdiAdress;
    @Column(name="sdi_payment") private Integer sdiPayment;
    @Column(name="bi_name") private String biName;
    @Column(name="bi_owner") private String biOwner;
    @Column(name="bi_business_number") private String biBusinessNumber;
    @Column(name="sdi_origin") private String sdiOrigin;
    
}
