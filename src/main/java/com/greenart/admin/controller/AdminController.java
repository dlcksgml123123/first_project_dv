package com.greenart.admin.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import com.greenart.admin.service.AdminInfoService;
import com.greenart.admin.service.OwnerInfoService;
import com.greenart.admin.vo.AdminAddVO;
import com.greenart.admin.vo.AdminLoginVO;
import com.greenart.admin.vo.StoreCategoryVO;

import jakarta.servlet.http.HttpSession;
import net.bytebuddy.TypeCache.Sort;
@Controller
// @RequestMapping(value = "/admin", method = RequestMethod.POST)
public class AdminController {
    @Autowired AdminInfoService adminInfoService;
    @Autowired OwnerInfoService ownerInfoService;
    @Value("${file.image.cate}") String cate_img_path;
    // Model model,@PageableDefault(size=5) Pageable pageable
    // sort="oiSeq" , direction = Sort.Direction.ASC
    @GetMapping("/owner/list")
    public String getOwnerList (Model model,Pageable pageable) {
        model.addAttribute("result", ownerInfoService.getOwnerList(pageable));
        return "/owner/list";
    }
    @PostMapping("/owner/login")
    public String postAdminLogin(AdminLoginVO login , HttpSession session, Model model){
        Map<String,Object> map = adminInfoService.loginAdmin(login);
         if((boolean)map.get("status")) {
            session.setAttribute("loginUser", map.get("login"));
            return "redirect:/main";
        }
        else{
            model.addAttribute("message", map.get("message"));
            return "/index";
        }
    }
    @GetMapping("/admin/add")
    public String getAdminAdd() {
        return "/owner/add";
    }

    @PostMapping("admin/add")
    public String postAddAdmin(AdminAddVO data, Model model) {
       Map<String,Object> map = adminInfoService.addAdmin(data);
       if((boolean)map.get("status")) {
        return "redirect:/";
       }
       model.addAttribute("inputdata",data);
       model.addAttribute("message",map.get("message"));
        return "/owner/add";
        }

@GetMapping("/cate/list") 
public String getCateList (Model model , @PageableDefault(size=5)Pageable pageable) {
       model.addAttribute("result", adminInfoService.getCateList(pageable));
        return "/cate/list";
    }

@GetMapping("/cate/add")
public String getCateAdd () {
    return "/cate/add";
}
// @RequestMapping(value = "/cate", method = RequestMethod.POST)
@PostMapping("/cate/add")
public String postCateadd(StoreCategoryVO data ) {
    adminInfoService.addCate(data);
    return "redirect:/cate/list";
}
}
    

