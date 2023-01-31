package com.greenart.yogio.lchwork.Controller;

import java.net.URLEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.lchwork.entity.OiMenuCateJoinEntity;
import com.greenart.yogio.lchwork.entity.OiMenuCategoryEntity;
import com.greenart.yogio.lchwork.entity.OiMenuInfoEntity;
import com.greenart.yogio.lchwork.entity.OiStoreInfoEntity;
import com.greenart.yogio.lchwork.repository.OiDeliveryInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiMenuInfoRepository;
import com.greenart.yogio.lchwork.repository.OiStoreInfoRepository;
import com.greenart.yogio.lchwork.service.StoreMenuService;
import com.greenart.yogio.lchwork.vo.MenuVO;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired OiDeliveryInfoRepository diRepo;
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired OiMenuCategoryRepository mcRepo;
    @Autowired OiMenuInfoRepository mniRepo;
    @Autowired OiMenuCateJoinRepository mcjRepo;
    @Autowired StoreMenuService storeMenuService;
    @Value("${file.image.menu}") String menu_img_path;

    @PutMapping("/add")

    // public Map<String, Object> putMenuAdd(@RequestBody MenuVO data){
    public Map<String, Object> putMenuAdd(MenuVO data, @RequestPart @Nullable MultipartFile file){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Path folderLocation = Paths.get(menu_img_path);
        if(file != null) {
            String originFileName = file.getOriginalFilename();
            String [] split = originFileName.split("\\."); 
            String ext = split[split.length -1];
            String filename = "";
            for (int i = 0; i<split.length-1; i++ ){
                filename += split[i];
            }
            String saveFilename = "menu_";
            Calendar c = Calendar.getInstance();
            saveFilename += c.getTimeInMillis()+"."+ext;
            Path targetFile = folderLocation.resolve(saveFilename);
        try {
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        data.setMni_img(filename);
        data.setMni_filename(saveFilename);
        }
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
                null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
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
                null, data.getMni_img(), data.getMni_name(), data.getMni_discount(), data.getMni_price(), data.getMni_filename()
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


     @GetMapping("/images/{uri}") 
   public ResponseEntity<Resource> getImage ( @PathVariable String uri, 
            HttpServletRequest request ) throws Exception { 
    Path folderLocation = Paths.get(menu_img_path);
    String filename = storeMenuService.getFilenameByImg(uri);
    // System.out.println(sService.getFilenameByUri(uri));
    String[] split = filename.split("\\.");
    String ext = split[split.length-1];
    String exportName = uri+"."+ext; 
    System.out.println(filename);

     Path targetFile = folderLocation.resolve(filename); 
     Resource r = null; 
     
     try { r = new UrlResource(targetFile.toUri()); }
      catch(Exception e) { e.printStackTrace(); } 
      
      String contentType = null;
      
    try { contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
      
    if(contentType == null) { 
      contentType = "application/octet-stream"; } }
     catch(Exception e) { e.printStackTrace(); } 
     return ResponseEntity.ok()
    .contentType(MediaType.parseMediaType(contentType)) 
    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+URLEncoder.encode(exportName, "UTF-8")+"\"")
    .body(r);
    }
}
