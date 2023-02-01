package com.greenart.yogio.admin.sang.vo;



import lombok.Data;

@Data
public class MenuCateVO {
    private Long mcjSeq;
    private Long pcSeq;
    private String topping_name;
    private Integer topping_price; 
    private Long mpjMcjSeq;
    private Long mpjPcjSeq;

}