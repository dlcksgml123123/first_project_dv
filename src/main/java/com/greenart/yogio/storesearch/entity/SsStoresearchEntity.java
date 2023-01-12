package com.greenart.yogio.storesearch.entity;





import org.hibernate.annotations.ColumnDefault;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "store_info")
@Entity
public class SsStoresearchEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq") private Long siSeq;
    @Column(name = "si_name") private String siName;
    @Column(name = "si_uri") private String siUri;
    @Column(name = "si_min_order_price") private Integer siMinOrderPrice;
    @Column(name = "si_discount_price") private Integer siDiscountPrice;
    @Column(name = "si_discount_condition") private String siDiscountCondition;
    @ManyToOne @JoinColumn(name = "si_di_seq") SsDeliveryEntity siDiSeq;
    // @Column(name = "si_di_seq") private Long siDiSeq;
    @Column(name = "si_clean_info") @ColumnDefault("1") private Long siCleanInfo;
    @Column(name = "si_file_name") private String siFileName;

   
    // @ManyToOne @JoinColumn(name = "si_mi_seq") SsMenuInfoEntity menuinfo;
    // public SsStoresearchEntity(SsMenuinfoVO data){
    //     this.siSeq = data.getSiSeq();
    //     this.siName = data.getSiName();
    //     this.siImg = data.getSiImg();
    //     this.siMop = data.getSiMop();
    //     this.siDp = data.getSiDp();
    //     this.siDc = data.getSiDc();
    //     this.siDiSeq = data.getSiDiSeq();
    //     this.siCi = data.getSiCi();
    
}

