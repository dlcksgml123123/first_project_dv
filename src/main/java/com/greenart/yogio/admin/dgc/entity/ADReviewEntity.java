package com.greenart.yogio.admin.dgc.entity;

import java.util.Date;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Immutable
@Table(name = "admin_review_view")
public class ADReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_seq")                private Long reSeq;
    @Column(name = "si_name")               private String siName;
    @Column(name = "mni_name")              private String mniName;
    @Column(name = "re_content")            private String reContent;
    @Column(name = "re_score")              private Integer reScore;
    @Column(name = "mi_nickname")           private String miNickname;
    @Column(name = "re_reg_dt")             private Date reRegDt;
}
