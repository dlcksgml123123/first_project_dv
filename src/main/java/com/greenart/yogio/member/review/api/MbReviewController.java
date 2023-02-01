package com.greenart.yogio.member.review.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.yogio.member.review.entity.MbReviewEntity;
import com.greenart.yogio.member.review.entity.MbReviewImageEntity;
import com.greenart.yogio.member.review.service.MbReviewService;
import com.greenart.yogio.member.review.vo.MbReviewVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/review")
public class MbReviewController {
    @Autowired MbReviewService rService;
    @PutMapping("/write") //리뷰쓰기
    public ResponseEntity<Object> writeReview(@RequestBody MbReviewVO data, @RequestParam Long miSeq) {
        Map<String, Object> resultMap = rService.addReview(data , miSeq);
        return new ResponseEntity<Object>(resultMap, (HttpStatus)resultMap.get("code"));
    }
}
