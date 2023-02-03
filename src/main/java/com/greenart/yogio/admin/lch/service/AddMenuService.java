package com.greenart.yogio.admin.lch.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.admin.lch.entity.MenuListViewEntity;
import com.greenart.yogio.admin.lch.repository.MenuListViewRepository;
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
import jakarta.transaction.Transactional;


@Service
public class AddMenuService {
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired OiMenuCategoryRepository mcRepo;
    @Autowired OiMenuInfoRepository mniRepo;
    @Autowired OiMenuCateJoinRepository mcjRepo;
    @Autowired StoreMenuService storeMenuService;
    @Autowired MenuListViewRepository menulistRepo;
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
        OiStoreInfoEntity store = siRepo.findBySiSeq(data.getMc_si_seq());
        OiMenuInfoEntity menu = mniRepo.findByMniName(data.getMni_name());
        if(store.getSiSeq() == null) {
            map.put("status", false);
            map.put("message", "등록된 가게가 없습니다.");
        }
        else {
            if(mcEntity == null) {
                mcEntity = new OiMenuCategoryEntity(
                null, data.getMc_name(), data.getMc_explanation(), data.getMc_si_seq()
                );
                mcRepo.save(mcEntity);

                if(menu == null) {
                    menu = new OiMenuInfoEntity(
                    null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
                    );
                    mniRepo.save(menu);

                    OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                    null, mcEntity.getMcSeq(), menu.getMniSeq());
                    mcjRepo.save(mcjEntity);
                }
                else {
                    OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                    null, mcEntity.getMcSeq(), menu.getMniSeq());
                    mcjRepo.save(mcjEntity);
                }

            }
            else {
                if(menu == null) {
                    menu = new OiMenuInfoEntity(
                    null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
                    );
                    mniRepo.save(menu);

                    OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                    null, mcEntity.getMcSeq(), menu.getMniSeq());
                    mcjRepo.save(mcjEntity);
                }
                else {
                    OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                    null, mcEntity.getMcSeq(), menu.getMniSeq());
                    mcjRepo.save(mcjEntity);
                }
            }
            map.put("status", true);
            map.put("message", "메뉴등록이 되었습니다.");
        }
        // if (mcEntity == null) {
            
        //     if (store.getSiSeq() == null) {
        //         map.put("status", false);
        //         map.put("message", "등록된 가게가 없습니다.");
        //     } 
        //     else {
                
                // mcEntity = new OiMenuCategoryEntity(
                //     null, data.getMc_name(), data.getMc_explanation(), data.getMc_si_seq()
                // );
        //         mcRepo.save(mcEntity);

        //         if(menu.getMniName() == null){
        //         OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
        //             null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
        //         );
        //         mniRepo.save(mniEntity);

        //         OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
        //             null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
        //         );
        //         mcjRepo.save(mcjEntity);

        //         map.put("status", true);
        //         map.put("message", "메뉴등록이 되었습니다.");
        //         }
        //         else {
        //             OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
        //             null, mcEntity.getMcSeq(), menu.getMniSeq());
        //             mcjRepo.save(mcjEntity);
        //             map.put("status", true);
        //             map.put("message", "메뉴등록이 되었습니다.");
        //         }
                
        //     }
        // } 
        // else {
        //     if(menu.getMniName() == null){
        //         OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
        //             null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
        //         );
        //         mniRepo.save(mniEntity);

        //         OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
        //             null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
        //         );
        //         mcjRepo.save(mcjEntity);

        //         map.put("status", true);
        //         map.put("message", "메뉴등록이 되었습니다.");
        //         }
        //         else {
        //             OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
        //             null, mcEntity.getMcSeq(), menu.getMniSeq());
        //             mcjRepo.save(mcjEntity);
        //             map.put("status", true);
        //             map.put("message", "메뉴등록이 되었습니다.");
        //         }
            // OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
            //     null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
            // );
            // mniRepo.save(mniEntity);

            // OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
            //     null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
            // );
            // mcjRepo.save(mcjEntity);

            // map.put("status", true);
            // map.put("message", "메뉴등록이 되었습니다.");
        // }
        return map;
    }
    // 메뉴 리스트 
    public Map<String, Object> getMenuList(Long storeNum, Pageable pageable) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if (storeNum == null) {
            Page<MenuListViewEntity> menu = menulistRepo.findAll(pageable);
            map.put("list", menu.getContent());
            map.put("total", menu.getTotalElements());
            map.put("totalPage", menu.getTotalPages());
            map.put("currentPage", menu.getNumber());
        }
        else {
        Page<MenuListViewEntity> page = menulistRepo.findBySiSeq(storeNum, pageable);
            map.put("list", page.getContent());
            map.put("total", page.getTotalElements());
            map.put("totalPage", page.getTotalPages());
            map.put("currentPage", page.getNumber());
        }
        return map;
    }
    // 메뉴 삭제
    @Transactional
    public void deleteMenu(Long menu_no) {
        mniRepo.deleteById(menu_no);
    }
    // 디테일 메뉴
    public Map<String, Object> selectMenuInfo(Long menu_no) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<OiMenuInfoEntity> entityOpt = mniRepo.findById(menu_no);
        if(entityOpt.isEmpty()) {
            resultMap.put("status", false);
        }
        else {
            resultMap.put("status", true);
            resultMap.put("no", entityOpt.get().getMniSeq());
            resultMap.put("name", entityOpt.get().getMniName());
            resultMap.put("price", entityOpt.get().getMniPrice());
            resultMap.put("discount", entityOpt.get().getMniDiscount());
        }
        return resultMap;
    }
    // 메뉴 수정
    public Map<String, Object> updateMenuInfo(Long no, String name) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<OiMenuInfoEntity> entityOpt = mniRepo.findById(no);
        if(entityOpt.isEmpty()) {
            resultMap.put("updated", true);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "잘못된 장르 정보입니다.");
        }
        else if(entityOpt.get().getMniName().equalsIgnoreCase(name)) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "기존 설정된 이름으로 변경 불가능합니다.");
        }
        else if(mniRepo.countByMniName(name) != 0) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", name+"메뉴는 이미 존재합니다.");
        }
        else {
            // OiMenuInfoEntity entity = OiMenuInfoEntity.builder().mniSeq(no).mniName(name).mniPrice(null)
        }
        return resultMap;
    }
}