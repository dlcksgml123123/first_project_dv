package com.greenart.yogio.storeInfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.admin.entity.OwnerNotinceImgEntity;
import com.greenart.yogio.storeInfo.entity.SiOwnerNotinceImgEntity;

@Repository
public interface SiOwnerNoticeImgRepostiory  extends JpaRepository <SiOwnerNotinceImgEntity , Long>{
         public List<SiOwnerNotinceImgEntity> findByOniImgPath(String uri);
}
