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
@Table(name = "menu_info")
public class MenuOptionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mni_seq") private Long mniSeq;
    @Column(name = "mni_price") private Integer mniPrice;
    @Column(name = "mni_name") private String mniName;
    @Column(name = "mni_img") private String mniImg;
    @Column(name = "mni_filename") private String mniFilename;
    @Column(name = "mni_discount") private Double mniDiscount;
    
}
