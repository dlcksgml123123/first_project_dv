package com.greenart.yogio.admin.sang.entity;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Immutable
@Table(name="option_list")
public class OptionLlistEntity {
    @Id
    @Column (name="pm_seq") private Long pmSeq;
    @Column (name="pm_name") private String pmName;
    @Column (name="mni_seq") private Long mniSeq;
    @Column (name="mni_name") private String mniName;
    @Column (name="mc_seq") private Long mcSeq;
    @Column (name="mc_name") private String mcName;
    @Column (name="pc_seq") private Long pcSeq;
    @Column (name="pc_name") private String pcName;
}
 