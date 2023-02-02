package com.greenart.yogio.storeInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.storeInfo.entity.SiOwnerNotinceImgEntity;
import com.greenart.yogio.storeInfo.repository.SiOwnerNoticeImgRepostiory;

@Service
public class SiStoreOwnerNoticeImgService {
     @Autowired SiOwnerNoticeImgRepostiory siOwnerNoticeImgRepostiory;
    public String getFilenameByUri(String uri) {
    List<SiOwnerNotinceImgEntity> data = siOwnerNoticeImgRepostiory.findByOniImgPath(uri);
    return data.get(0).getOniFileName();
  }
}
