package com.greenart.yogio.admin.dgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.yogio.admin.dgc.service.ADReviewService;

import jakarta.annotation.Nullable;

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
}
