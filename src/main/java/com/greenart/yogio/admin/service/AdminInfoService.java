package com.greenart.yogio.admin.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.admin.entity.AdminInfoEntity;
import com.greenart.yogio.admin.entity.StoreCategoryEntity;
import com.greenart.yogio.admin.repository.AdminInfoRepository;
import com.greenart.yogio.admin.repository.StoreCategoryRepository;
import com.greenart.yogio.admin.vo.AdminAddVO;
import com.greenart.yogio.admin.vo.AdminLoginVO;
import com.greenart.yogio.admin.vo.AdminVO;
import com.greenart.yogio.admin.vo.StoreCategoryVO;

@Service
public class AdminInfoService {
    @Value("${file.image.cate}") String cate_img_path;
    @Autowired StoreCategoryRepository storeCategoryRepository;
    @Autowired AdminInfoRepository adminInfoRepository;
    public Map<String, Object> loginAdmin(AdminLoginVO login) {
        Map<String,Object> resultMap= new LinkedHashMap<String, Object>();
        AdminInfoEntity entity =  adminInfoRepository.findByAiIdAndAiPwd(login.getAi_id(), login.getAi_pwd());
        if (entity == null) {
            resultMap.put("status", false);
            resultMap.put("message","아이디 혹은 비밀번호 오류입니다.");
        }
        else if(entity.getAiStatus() == 2 ) {
            resultMap.put("status", false);
            resultMap.put("message", "등록대기중인 계정입니다.");
        }
        else if(entity.getAiStatus() == 3 ) {
            resultMap.put("status", false);
            resultMap.put("message", "이용정지된 계정입니다.");
        }
        else {
            resultMap.put("status", true);
            resultMap.put("message", "로그인되었습니다.");
            resultMap.put("login", new AdminVO(entity));
        }
        return resultMap;
}
 public Map<String, Object> addAdmin(AdminAddVO data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if (data.getId() == null || data.getId().equals("")) {
            map.put("status", false);
            map.put("message", "아이디를 입력하세요");
        }
        else if(adminInfoRepository.countByAiId(data.getId()) !=0){
            map.put("status", false);
            map.put("message", data.getId()+"은/는 이미 사용중입니다.");
        }
        else if (data.getPwd() == null || data.getPwd().equals("")) {
            map.put("status", false);
            map.put("message", "비밀번호를 입력하세요");
        }
        else if (data.getName() == null || data.getName().equals("")) {
            map.put("status", false);
            map.put("message", "이름을 입력하세요");
        }
        else {
            AdminInfoEntity entity = AdminInfoEntity.builder().aiId(data.getId()).aiPwd(data.getPwd()).
            aiName(data.getName()).aiGrade(data.getType()).aiGrade(2).build();
            adminInfoRepository.save(entity);
            map.put("status", true);
            map.put("message", "관리자 계정 등록 신청 완료");
        }

        return map;
    }
        public Map<String,Object> getCateList(Pageable pageable){
        Page<StoreCategoryEntity> page= storeCategoryRepository.findAll(pageable);
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("cateList", page);
        resultMap.put("total", page.getTotalElements());
        resultMap.put("totalpage", page.getTotalPages());
        resultMap.put("currentPage", page.getNumber());
        return resultMap;
    }

    public void addCate (StoreCategoryVO data){
        MultipartFile file = data.getFile();
    Path folderLocation = Paths.get(cate_img_path);
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
    StoreCategoryEntity entity = StoreCategoryEntity.builder().scName(data.getScName()).scImage(filename).build();
    storeCategoryRepository.save(entity);

    }
}
