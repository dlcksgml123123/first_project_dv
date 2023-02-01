package com.greenart.yogio.admin.sang.vo;



import com.greenart.yogio.admin.sang.entity.PlusCategotyEntity;
import com.greenart.yogio.admin.sang.entity.PlusMenuJoinEntity;



import lombok.Data;


@Data
public class MenuCateOptionVO {
    private Long pcSeq;
    private Long pcjSeq;
    private Long pcjPcSeq;
    private Long pcjPmSeq;
    private Long pmSeq;
    private String pcName;
    private Integer pcMultiChoice;
    private Integer pcEssentialChoice;
    private String pmName;
    private Integer pmPrice;
   
    
public MenuCateOptionVO( PlusMenuJoinEntity plusMenuJoinenti, PlusCategotyEntity pluscategotyenti){
        this.pcSeq =  pluscategotyenti.getPcSeq();
        this.pmSeq =  plusMenuJoinenti.getPmSeq();
        this.pcName =  pluscategotyenti.getPcName();
        this.pcMultiChoice =  pluscategotyenti.getPcMultiChoice();
        this.pcEssentialChoice =  pluscategotyenti.getPcEssentialChoice();
        this.pmName =  plusMenuJoinenti.getPmName();
        this.pmPrice = plusMenuJoinenti.getPmPrice(); 
   

     

}
}