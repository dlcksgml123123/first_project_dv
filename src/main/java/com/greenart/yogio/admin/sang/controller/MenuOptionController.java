package com.greenart.yogio.admin.sang.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.greenart.yogio.admin.sang.service.MenuOptionService;
import io.micrometer.common.lang.Nullable;



@Controller
@RequestMapping("/menuoption")
public class MenuOptionController {
    @Autowired MenuOptionService meoptionservice;
    @GetMapping("/menulist")
    public String getMenuInfo(Model model, @RequestParam @Nullable String keyword,
    @PageableDefault(size=10,sort = "mniSeq", direction = Sort.Direction.ASC) Pageable pageable){
        if(keyword==null) keyword ="";
        model.addAttribute("result", meoptionservice.AllList(keyword, pageable));
        model.addAttribute("keyword",keyword);
        return "/menuoption/list";
    }
}

