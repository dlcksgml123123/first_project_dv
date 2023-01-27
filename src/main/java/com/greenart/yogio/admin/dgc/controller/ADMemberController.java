package com.greenart.yogio.admin.dgc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.yogio.admin.dgc.service.ADMemberService;
import com.greenart.yogio.admin.dgc.vo.ADMemberAddVO;
import com.greenart.yogio.admin.vo.AdminVO;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admember")
public class ADMemberController {
  @Autowired ADMemberService aService;
  @GetMapping("/add")
  public String getAddMember() {
    return "/dgc/add";
  }
  @PostMapping("/add")
  public String postAddMember(ADMemberAddVO data, Model model) {
    Map<String,Object> map = aService.addMember(data);
    if((boolean)map.get("status")) {
      return "redirect:/";
    }
    model.addAttribute("inputdata", data);
    model.addAttribute("message", map.get("message"));
    return "/dgc/add";
  }
  // @GetMapping("/list")
  // public String getMemberList(Model model, @RequestParam @Nullable String keyword,
  // @PageableDefault(size=10, sort="miSeq", direction = Sort.Direction.DESC) Pageable pageable
  // , HttpSession session
  // ) {
  //   // session.setAttribute("update_result", null);
  //   // AdminVO admin = (AdminVO)session.getAttribute("loginUser");
  //   // if(admin == null) {
  //   //   return "redirect:/";
  //   // }
  //   // else if(admin.ai_grade() != 99) {
  //   //   return "redirect:/main";
  //   // }
  //   // size : 한 페이지 당 출력 할 row 수
  //   // sort : 정렬 기준이 될 엔터티 변수 명
  //   // direction : 정렬 방향 (오름차순, 내림차순)
  //   // model.addAttribute("list", adminService.getAdminList());
  //   if(keyword==null) keyword= "";
  //   // model.addAttribute("result", aService.getMemberList(keyword, pageable));
  //   model.addAttribute("keyword", keyword);
  //   return "/admin/list";
  // }
}
