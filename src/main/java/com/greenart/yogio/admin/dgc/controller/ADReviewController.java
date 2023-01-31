package com.greenart.yogio.admin.dgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.yogio.admin.dgc.service.ADReviewService;
import com.greenart.yogio.admin.vo.admin.AdminVO;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/adreview")
public class ADReviewController {
  @Autowired ADReviewService rService;

  @GetMapping("/list")
  public String getAdminList(Model model, Pageable pageable, @RequestParam @Nullable String keyword){
      if(keyword == null) keyword = "";
      model.addAttribute("result",rService.getReviewList(pageable, keyword));
      return "/dgc/review";
  }

  @GetMapping ("/delete") 
  public String getReviewDelete(@RequestParam Long review_no,@RequestParam Integer page, @RequestParam @Nullable String keyword,HttpSession session) {
      AdminVO admin = (AdminVO)session.getAttribute("loginUser");
      if(admin == null) { // 로그인 상태가 아니라면
          return "redirect:/"; // 로그인 페이지로
      }
      else if (admin.getAi_grade() != 99) { // 로그인 했는데 마스터가 아니라면
          return "redirect:/main"; // 메인페이지로
      }
      rService.deleteReview(review_no);
      String returnValue = "";
      if(keyword == null || keyword.equals("")) returnValue = "redirect:/adreview/list?page="+page;
      else returnValue = "redirect:/adreview/list?page="+page+"&keyword="+keyword;
      return returnValue;
  }
}
