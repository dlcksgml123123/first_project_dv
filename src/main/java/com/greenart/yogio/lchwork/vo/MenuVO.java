package com.greenart.yogio.lchwork.vo;

import org.springframework.web.multipart.MultipartFile;

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
    private String mc_name; // 카테고리 명
    private String mc_explanation; // null
    private Long mc_si_seq; // 가게번호
    // 메뉴 정보 4
    // mni_seq
    private String mni_img; // 자동
    private String mni_name; 
    private Double mni_discount; // null
    private Integer mni_price;
    private String mni_filename; // 자동
    // private MultipartFile file;
// 메뉴 카테 연결 5
// mcj_seq
// mcj_mc_seq
// mcj_mni_seq

// mni_img -> 파일 올리면 o 파일 안올리면 null
// filename -> 그냥 자동생성

// mc_si_seq, mc_name, (mc_explanation), mni_name, mni_price, (mni_discount)


}
