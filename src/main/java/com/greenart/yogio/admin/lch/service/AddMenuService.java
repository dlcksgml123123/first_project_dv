package com.greenart.yogio.admin.lch.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.lchwork.entity.OiMenuCateJoinEntity;
import com.greenart.yogio.lchwork.entity.OiMenuCategoryEntity;
import com.greenart.yogio.lchwork.entity.OiMenuInfoEntity;
import com.greenart.yogio.lchwork.entity.OiStoreInfoEntity;
import com.greenart.yogio.lchwork.repository.OiMenuCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiMenuInfoRepository;
import com.greenart.yogio.lchwork.repository.OiStoreInfoRepository;
import com.greenart.yogio.lchwork.service.StoreMenuService;
import com.greenart.yogio.lchwork.vo.MenuVO;

import jakarta.annotation.Nullable;


@Service
public class AddMenuService {
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired OiMenuCategoryRepository mcRepo;
    @Autowired OiMenuInfoRepository mniRepo;
    @Autowired OiMenuCateJoinRepository mcjRepo;
    @Autowired StoreMenuService storeMenuService;
    @Value("${file.image.menu}") String menu_img_path;

    public Map < String, Object > addMenuInfo(MenuVO data, MultipartFile file) {
        Map < String, Object > map = new LinkedHashMap < String, Object > ();
        Path folderLocation = Paths.get(menu_img_path);
        if (file != null) {
            String originFileName = file.getOriginalFilename();
            String[] split = originFileName.split("\\.");
            String ext = split[split.length - 1];
            String filename = "";
            for (int i = 0; i < split.length - 1; i++) {
                filename += split[i];
            }
            String saveFilename = "menu_";
            Calendar c = Calendar.getInstance();
            saveFilename += c.getTimeInMillis() + "." + ext;
            Path targetFile = folderLocation.resolve(saveFilename);
            try {
                Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
            data.setMni_img(filename);
            data.setMni_filename(saveFilename);
            map.put("filename", filename);
            map.put("saveFilename", saveFilename);
        }
        OiMenuCategoryEntity mcEntity = mcRepo.findByMcNameAndMcSiSeq(data.getMc_name(), data.getMc_si_seq());
        if (mcEntity == null) {
            System.out.println("mcsiseq : "+data.getMc_si_seq());
            OiStoreInfoEntity store = siRepo.findBySiSeq(data.getMc_si_seq());
            if (store == null) {
                map.put("status", false);
                map.put("message", "등록된 가게가 없습니다.");
            } 
            else {
                mcEntity = new OiMenuCategoryEntity(
                    null, data.getMc_name(), data.getMc_explanation(), data.getMc_si_seq()
                );
                mcRepo.save(mcEntity);

                OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
                    null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
                );
                System.out.println(mniEntity);
                mniRepo.save(mniEntity);

                OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                    null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
                );
                mcjRepo.save(mcjEntity);
                map.put("status", true);
                map.put("message", "메뉴등록이 되었습니다.");
            }
        } else {
            OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
                null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
            );
            mniRepo.save(mniEntity);
            OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
            );
            mcjRepo.save(mcjEntity);
            map.put("status", true);
            map.put("message", "메뉴등록이 되었습니다.");



        }
        return map;
    }
}