package com.greenart.yogio.admin.sang.service;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.sang.entity.OptionLlistEntity;
import com.greenart.yogio.admin.sang.repository.OptionListRepository;



@Service
public class MenuOptionService {
     @Autowired OptionListRepository optionRepo;
    public Map<String,Object>AllList(Long mniSeq, Pageable pageable){
        Page<OptionLlistEntity> page = optionRepo.findByMniSeq(mniSeq, pageable);
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        resultMap.put("list",page.getContent());
        resultMap.put("total",page.getTotalElements());
        resultMap.put("totalPage",page.getTotalPages());
        resultMap.put("currentPage",page.getNumber());
        return resultMap;
        } 
    
}
    



