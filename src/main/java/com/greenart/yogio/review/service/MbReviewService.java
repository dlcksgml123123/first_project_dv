package com.greenart.yogio.review.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.review.entity.MbReviewEntity;
import com.greenart.yogio.review.repository.MbReviewRepository;

@Service
public class MbReviewService {
    @Autowired MbReviewRepository r_repo;
    public Map<String, Object> addReview(MbReviewEntity data) { //리뷰쓰기
     Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();
     
     return resultMap;
    }
}
