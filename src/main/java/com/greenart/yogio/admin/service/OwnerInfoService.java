package com.greenart.yogio.admin.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.entity.OwnerInfoEntity;
import com.greenart.yogio.admin.repository.OwnerInfoRepostitory;
import com.greenart.yogio.admin.vo.OwnerInfoVO;

@Service
public class OwnerInfoService {
    @Autowired OwnerInfoRepostitory ownerInfoRepostitory;
    public Map<String,Object> getOwnerList(Pageable pageable){
        Page<OwnerInfoEntity> page= ownerInfoRepostitory.findAll(pageable);
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("ownerList", page);
        return resultMap;
    }
}
