package com.greenart.yogio.admin.lch.controller;

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
    // 메뉴 추가
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
            return "/lch/add";
        }

    } 

    // 메뉴 리스트
    @GetMapping("/menulist")
    public String getMenuMenulist(Model model, @RequestParam @Nullable Long storeNum,
    @PageableDefault(size=10, sort="mniSeq",direction = Sort.Direction.DESC) Pageable pageable
    ){
        model.addAttribute("result", addService.getMenuList(storeNum, pageable));
        model.addAttribute("storeNum", storeNum);
        return "lch/menulist";
    }
    // 메뉴 삭제
    @GetMapping("/menudelete")
    public String getMenudelete(@RequestParam Long menu_no) {
        addService.deleteMenu(menu_no);
        return "redirect:/menu/menulist";
    }
    // 디테일 메뉴
    @GetMapping("/menudetail")
    public String getMenudetail(@RequestParam Long menu_no, @RequestParam @Nullable Integer page, @RequestParam String keyword, Model model) {
        if(page == null) page = 0;
        if(keyword == null) keyword = "";
        Map<String, Object> map = addService.selectMenuInfo(menu_no);
        map.put("message", null);
        model.addAttribute("menu", map);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "lch/menudetail";
    }
    
}
