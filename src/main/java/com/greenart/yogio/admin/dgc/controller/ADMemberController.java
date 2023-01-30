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
import com.greenart.yogio.admin.vo.admin.AdminVO;

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
    // if((boolean)map.get("status")) {
    //   return "redirect:/";
    // }
    model.addAttribute("inputdata", data);
    model.addAttribute("message", map.get("message"));
    return "/dgc/add";
  }
  @GetMapping("/list")
    public String getAdminList(Model model, Pageable pageable, @RequestParam @Nullable String keyword){
        if(keyword == null) keyword = "";
        model.addAttribute("result",aService.getMemberList(pageable, keyword));
        return "/dgc/list";
    }
    @GetMapping("/update/status")
    public String getMemberUpdateStatus (@RequestParam Integer value ,@RequestParam Long member_no,
    @RequestParam Integer page, @RequestParam @Nullable String keyword , HttpSession session) {
         AdminVO admin = (AdminVO)session.getAttribute("loginUser");
        if(admin == null) { // 로그인 상태가 아니라면
            return "redirect:/"; // 로그인 페이지로
        }
        else if (admin.getAi_grade() != 99) { // 로그인 했는데 마스터가 아니라면
            return "redirect:/main"; // 메인페이지로
        }
        aService.updateMemberStatus(value,member_no);
        String returnValue = "";
        if(keyword == null || keyword.equals("")) returnValue = "redirect:/admember/list?page="+page;
        else returnValue = "redirect:/admember/list?page="+page+"&keyword="+keyword;
        return returnValue;
    }
    @GetMapping ("/delete") 
    public String getMemberDelete(@RequestParam Long member_no,@RequestParam Integer page, @RequestParam @Nullable String keyword,HttpSession session) {
        AdminVO admin = (AdminVO)session.getAttribute("loginUser");
        if(admin == null) { // 로그인 상태가 아니라면
            return "redirect:/"; // 로그인 페이지로
        }
        else if (admin.getAi_grade() != 99) { // 로그인 했는데 마스터가 아니라면
            return "redirect:/main"; // 메인페이지로
        }
        aService.deleteMember(member_no);
        String returnValue = "";
        if(keyword == null || keyword.equals("")) returnValue = "redirect:/admember/list?page="+page;
        else returnValue = "redirect:/admember/list?page="+page+"&keyword="+keyword;
        return returnValue;
    }
}
