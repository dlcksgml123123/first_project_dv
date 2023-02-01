package com.greenart.yogio.admin.sang.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenart.yogio.admin.sang.service.MenuCateService;
import com.greenart.yogio.admin.sang.vo.MenuCateOptionInsertVO;
import com.greenart.yogio.admin.sang.vo.MenuCateVO;






@Controller
@RequestMapping("/menucate")
public class MenuCateInfo {
    @Autowired MenuCateService menuCateSer;
    @GetMapping("/optionadd")
    public String postPcNameString(){
        return "/menucate/optionadd";
    }
    @PostMapping("/optionadd")
    public String postPcNameString(MenuCateOptionInsertVO data ,Model model){
        System.out.println(data);
        Map<String,Object> map = menuCateSer.addoptionInfo1(data);
        if((Boolean)map.get("status")){
            model.addAttribute("result", map);
            return "redirect:/menucate/optionadd";  
        }
        else{
            model.addAttribute("name", data);
            model.addAttribute("result", map);
            return "/menucate/optionadd";
        }}

    
    @GetMapping("/cateadd")
    public String getMcNameString(){
        return "/menucate/cateadd";
    }
    @PostMapping("/cateadd")
    public String getMcNameString(MenuCateVO data ,Model model){
        System.out.println(data);
        Map<String,Object> map = menuCateSer.addoptionInfo2(data);
        if((Boolean)map.get("status")){
            model.addAttribute("result", map);
            return "redirect:/menucate/cateadd";
        }
        else{
            model.addAttribute("name", data);
            model.addAttribute("result", map);
            return "/menucate/cateadd";
        }}
        
}
