package com.greenart.yogio.review.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.review.entity.MbReviewEntity;
import com.greenart.yogio.review.service.MbReviewService;

@RestController
@RequestMapping("/review")
public class MbReviewController {
    @Autowired MbReviewService rService;
    @PutMapping("/write") //리뷰쓰기
    public ResponseEntity<Object> writeReview(@RequestBody MbReviewEntity data) {
        Map<String, Object> resultMap = rService.addReview(data);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
