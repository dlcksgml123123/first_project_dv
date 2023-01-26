package com.greenart.yogio.admin.dgc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenart.yogio.admin.dgc.service.ADMemberService;
import com.greenart.yogio.admin.dgc.vo.ADMemberAddVO;

@Controller
@RequestMapping("/admember")
public class ADMemberController {
  @Autowired ADMemberService aService;
  
  @GetMapping("/add")
  public String getAddMember() {
    return "/dgc/add";
  }
  
  @PostMapping("/add")
  public String postAddMember(ADMemberAddVO data, Model model /* ,  @RequestPart MultipartFile adminImg*/) {
    Map<String,Object> map = aService.addMember(data);
    if((boolean)map.get("status")) {
      return "redirect:/";
    }
    model.addAttribute("inputdata", data);
    model.addAttribute("message", map.get("message"));
    return "/dgc/add";
  }
}
