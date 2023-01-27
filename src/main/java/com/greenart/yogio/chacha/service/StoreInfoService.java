package com.greenart.yogio.chacha.service;

import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.chacha.entity.StoreInfoEntity;
import com.greenart.yogio.chacha.repository.DeliveryInfoRepository;
import com.greenart.yogio.chacha.repository.StoreInfoRepository;
import com.greenart.yogio.chacha.vo.StoreInfoVO;

@Service
public class StoreInfoService  {
    @Autowired DeliveryInfoRepository deliveryInfoRepository;
    @Autowired StoreInfoRepository storeInfoRepository;
    @Value("${file.image.store}") String store_img_path;
     public void addCate (StoreInfoVO data){
    MultipartFile file = data.getFile();
    Path folderLocation = Paths.get(store_img_path);
    String originFileName = file.getOriginalFilename();
    String [] split = originFileName.split("\\.");
    String ext = split[split.length - 1];
    String filename = " ";
    for(int i = 0; i<split.length-1; i++) {
        filename += split[i];
    }

    String saveFilename = " ";
    Calendar c = Calendar.getInstance();
    saveFilename += c.getTimeInMillis()+"."+ext;
    Path targetFile = folderLocation.resolve(saveFilename);
    try{
    Files.copy(file.getInputStream(), targetFile,StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // StoreInfoEntity entity = new StoreInfoEntity(data.getSiName(),data.getSiDiscountCondition(),
    // data.getSiDiscountPrice(),data.getSiMinOrderPrice(),data.g(filename).siFileName(saveFilename).
    // siCleanInfo(data.getSiCleanInfo()))
    }

}
