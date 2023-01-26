package com.greenart.yogio.lchwork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.lchwork.entity.OiMenuInfoEntity;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMenuInfoRepository;

@Service
public class StoreMenuService {
    @Autowired OiMenuInfoRepository oiMenuInfoRepository;
      public String getFilenameByImg(String uri) {
    List<OiMenuInfoEntity> data = oiMenuInfoRepository.findByMniImg(uri);
    return data.get(0).getMniFilename();
  }
    
}
