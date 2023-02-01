package com.greenart.yogio.admin.sang.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.admin.sang.entity.PlusCateJoinEntity;
import com.greenart.yogio.admin.sang.entity.MenuplusjoinEntity;
import com.greenart.yogio.admin.sang.entity.PlusCategotyEntity;
import com.greenart.yogio.admin.sang.entity.PlusMenuJoinEntity;
import com.greenart.yogio.admin.sang.repository.PlusCateJoinRepository;
import com.greenart.yogio.admin.sang.repository.PlusCategoryRepository;
import com.greenart.yogio.admin.sang.repository.PlusMenuJoinRepository;
import com.greenart.yogio.admin.sang.repository.MenuplusjoinRepository;
import com.greenart.yogio.admin.sang.vo.MenuCateOptionInsertVO;
import com.greenart.yogio.admin.sang.vo.MenuCateVO;




@Service
public class MenuCateService {
    @Autowired PlusCateJoinRepository joinrepo;
    @Autowired PlusCategoryRepository pcgrepo;
    @Autowired PlusMenuJoinRepository pmjrepo;
    @Autowired MenuplusjoinRepository menurepo;

    public Map<String, Object> addoptionInfo1(MenuCateOptionInsertVO data) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if(pcgrepo.countByPcName(data.getName()) == 0) {
                PlusCategotyEntity cateEntity = PlusCategotyEntity.builder()
                .pcName(data.getName())
                .pcMultiChoice(data.getPc_multi_choice())
                .pcEssentialChoice(data.getPc_essential_choice())
                .build();
                pcgrepo.save(cateEntity);
                map.put("status", true);
                map.put("message", "토핑카테고리정보를 추가하였습니다");
            }
            return map;
    }
    
//}

    public Map<String, Object> addoptionInfo2(MenuCateVO data2) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        PlusMenuJoinEntity toppingEntity = PlusMenuJoinEntity.builder()
            .pmName(data2.getTopping_name())
            .pmPrice(data2.getTopping_price())
            .build();
            pmjrepo.save(toppingEntity);

            PlusCateJoinEntity optionCateJoin = PlusCateJoinEntity.builder().pcjPcSeq(data2.getPcSeq()).pcjPmSeq(toppingEntity.getPmSeq()).build();
            joinrepo.save(optionCateJoin);

            MenuplusjoinEntity menuolusJoin = MenuplusjoinEntity.builder().mpjMcjSeq(data2.getMcjSeq()).mpjPcjSeq(optionCateJoin.getPcjSeq()).build();
            menurepo.save(menuolusJoin);

            map.put("status", true);
            map.put("message", "토핑정보를 추가하였습니다");
            return map;
    }


}
    
// }


    //     Map<String, Object> resultMap =  new LinkedHashMap<String,Object>();
    //     if(pcgrepo.countByPcName(data.getName()) == 0) {
    //         PlusCategotyEntity cateEntity = PlusCategotyEntity.builder()
    //         .pcName(data.getName())
    //         .pcMultiChoice(data.getPc_multi_choice())
    //         .pcEssentialChoice(data.getPc_essential_choice())
    //         .build();
    //         pcgrepo.save(cateEntity);
    //     }
    //     return resultMap;
    // }



    //         PlusCateJoinEntity optionCateJoin = PlusCateJoinEntity.builder().pcjPcSeq(cateEntity.getPcSeq()).pcjPmSeq(toppingEntity.getPmSeq()).build();
    //         joinrepo.save(optionCateJoin);
    //     }
    //     else {
    //         PlusMenuJoinEntity toppingEntity = PlusMenuJoinEntity.builder()
    //         .pmName(data.getTopping_name())
    //         .pmPrice(data.getTopping_price())
    //         .build();
    
    //         pmjrepo.save(toppingEntity);
    //         // pcgrepo.findBy
    //         resultMap.put("status", true);
    //         resultMap.put("message", "중복되는 토핑 카테고리를 제외, 토핑정보를 추가하였습니다");
    //         return resultMap;
    //     }
    
    

