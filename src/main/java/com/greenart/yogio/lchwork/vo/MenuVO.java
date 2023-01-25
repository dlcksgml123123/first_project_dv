package com.greenart.yogio.lchwork.vo;

import lombok.Data;

@Data
public class MenuVO {
// 배달정보 1
// di_seq
    // private Integer di_distance;
    // private Integer di_delivery_price;
    // private String di_time;
    // 가게정보 2
    // private Long si_seq;
    // private String si_name;
    // private String si_uri;
    // private Integer si_min_order_price;
    // private Integer si_discount_price;
    // private String si_discount_condition;
    // si_di_seq
    // private Integer si_clean_info;
    // private String si_file_name;
    // 메뉴 카테고리 3
    // mc_seq
    private String mc_name;
    private String mc_explanation;
    private Long mc_si_seq;
    // 메뉴 정보 4
    // mni_seq
    private String mni_img;
    private String mni_name;
    private Double mni_discount;
    private Integer mni_price;
    private String mni_filename;
// 메뉴 카테 연결 5
// mcj_seq
// mcj_mc_seq
// mcj_mni_seq

}
