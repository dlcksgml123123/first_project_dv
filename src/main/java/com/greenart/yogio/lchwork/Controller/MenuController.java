package com.greenart.yogio.lchwork.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.lchwork.entity.OiDeliveryInfoEntity;
import com.greenart.yogio.lchwork.entity.OiMenuCateJoinEntity;
import com.greenart.yogio.lchwork.entity.OiMenuCategoryEntity;
import com.greenart.yogio.lchwork.entity.OiMenuInfoEntity;
import com.greenart.yogio.lchwork.entity.OiStoreInfoEntity;
import com.greenart.yogio.lchwork.repository.OiDeliveryInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiMenuInfoRepository;
import com.greenart.yogio.lchwork.repository.OiStoreInfoRepository;
import com.greenart.yogio.lchwork.vo.MenuVO;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired OiDeliveryInfoRepository diRepo;
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired OiMenuCategoryRepository mcRepo;
    @Autowired OiMenuInfoRepository mniRepo;
    @Autowired OiMenuCateJoinRepository mcjRepo;

    @PutMapping("/add")
    // public Map<String, Object> putMenuAdd(@RequestBody MenuVO data){
    public Map<String, Object> putMenuAdd(MenuVO data, @RequestPart @Nullable MultipartFile file){
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        // OiDeliveryInfoEntity diEntity = diRepo.findByDiDistanceAndDiDeliveryPriceAndDiTime(
        // data.getDi_distance(), data.getDi_delivery_price(), data.getDi_time());
        // if(diEntity == null) {
        //     diEntity = new OiDeliveryInfoEntity(
        //         null, data.getDi_distance(), data.getDi_delivery_price(), data.getDi_time()
        //     );
        //     diRepo.save(diEntity);
        // }
        // OiStoreInfoEntity siEntity = siRepo.findBySiName(data.getSi_name());
        // if(siEntity == null) {
        //     siEntity = new OiStoreInfoEntity(
        //         null, data.getSi_name(), data.getSi_uri(), data.getSi_min_order_price(),
        //         data.getSi_discount_price(), data.getSi_discount_condition(), diEntity.getDiSeq(),
        //         data.getSi_clean_info(),data.getSi_file_name()
        //     );
        //     siRepo.save(siEntity);
        // }
        OiMenuCategoryEntity mcEntity = mcRepo.findByMcNameAndMcSiSeq(data.getMc_name(), data.getMc_si_seq());
        if(mcEntity == null) {
            OiStoreInfoEntity store = (OiStoreInfoEntity)siRepo.findBySiSeq(data.getMc_si_seq());
            if(store == null) {
                map.put("message", "등록된 가게가 없습니다.");
            }
            else {
            mcEntity = new OiMenuCategoryEntity(
                null, data.getMc_name(), data.getMc_explanation(), data.getMc_si_seq()
            );
            mcRepo.save(mcEntity);
        
            OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
                null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price()
            );
            mniRepo.save(mniEntity);
            OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
            );
            mcjRepo.save(mcjEntity);
            
            map.put("mcEntity", mcjEntity);
            map.put("mniEntity", mniEntity);
            map.put("mcEntity", mcEntity);
            }
        }
        else{
            OiMenuInfoEntity mniEntity = new OiMenuInfoEntity(
                null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price()
            );
            mniRepo.save(mniEntity);
            OiMenuCateJoinEntity mcjEntity = new OiMenuCateJoinEntity(
                null, mcEntity.getMcSeq(), mniEntity.getMniSeq()
            );
            mcjRepo.save(mcjEntity);
            
            map.put("mcjEntity", mcjEntity);
            map.put("mniEntity", mniEntity);
            map.put("mcEntity", mcEntity);
            
        }
        return map;
    }
}
