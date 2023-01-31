package com.greenart.yogio.admin.lch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.admin.lch.service.AddMenuService;
import com.greenart.yogio.lchwork.repository.OiStoreInfoRepository;
import com.greenart.yogio.lchwork.vo.MenuVO;

import io.micrometer.common.lang.Nullable;

@Controller
@RequestMapping("/menu")
public class AddMenuController {
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired AddMenuService addService;

    @GetMapping("/add")
    public String getMenuAdd() {
        return "/lch/add";
    }
    
    @PostMapping("/add")
    public String postMenuAdd(
    MenuVO data, @RequestPart @Nullable MultipartFile file, Model model 
    ) {
        System.out.println(file.getOriginalFilename());
        Map<String, Object> map = addService.addMenuInfo(data, file);
        if(!(Boolean)map.get("status")) {
            model.addAttribute("data", data);
            // model.addAttribute("mcName", data.getMc_name());
            // model.addAttribute("mcExplanation", data.getMc_explanation());
            // model.addAttribute("mniName", data.getMni_name());
            // model.addAttribute("mniPrice", data.getMni_price());
            // model.addAttribute("mniDiscount", data.getMni_discount());
            // model.addAttribute("mniImg", map.get("filename"));
            // model.addAttribute("mniFilename", map.get("saveFilename"));
            // model.addAttribute("file", file);
            // model.addAttribute("result", map);
            return "/lch/add";
        }
        else {
            return "redirect:/";
        }

    } 
}
