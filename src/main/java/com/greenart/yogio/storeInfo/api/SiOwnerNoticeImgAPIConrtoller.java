package com.greenart.yogio.storeInfo.api;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.storeInfo.entity.SiOwnerNotinceImgEntity;
import com.greenart.yogio.storeInfo.repository.SiOwnerNoticeImgRepostiory;
import com.greenart.yogio.storeInfo.service.SiStoreOwnerNoticeImgService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SiOwnerNoticeImgAPIConrtoller {
  @Autowired SiStoreOwnerNoticeImgService siStoreOwnerNoticeImgService;
   @Autowired SiOwnerNoticeImgRepostiory siOwnerNoticeImgRepostiory;
    @Value("${file.image.notice}") String notice_image_path;

    @GetMapping("/notice/images/{uri}") 
   public ResponseEntity<Resource> getImage ( @PathVariable String uri,  HttpServletRequest request ) throws Exception { 
    Path folderLocation = Paths.get(notice_image_path);
    String filename = siStoreOwnerNoticeImgService.getFilenameByUri(uri);
   
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

    @PutMapping("/notice/add") 
    public ResponseEntity<Object> putOwnerInfo(SiOwnerNotinceImgEntity data ,@RequestPart MultipartFile file) {
        Map<String, Object>  map = new LinkedHashMap<String, Object>();
        Path folderLocation =  Paths.get(notice_image_path);
        String originFileName = file.getOriginalFilename();
        String [] split = originFileName.split("\\."); 
        String ext = split[split.length -1];
        String filename = "";
        for (int i = 0; i<split.length-1; i++ ){
            filename += split[i];
        }
        String saveFilename = "store_";
        Calendar c = Calendar.getInstance();
        saveFilename += c.getTimeInMillis()+"."+ext;
        Path targetFile = folderLocation.resolve(saveFilename);
      try {
        Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
      } catch (Exception e) {
        e.printStackTrace();
      }
      SiOwnerNotinceImgEntity entity = new SiOwnerNotinceImgEntity(null, (long) 1, filename, null, saveFilename);
      siOwnerNoticeImgRepostiory.save(entity);
      
      map.put("message", "사장님 알린 완료됐습니다.");
      
      return new ResponseEntity<>(map,HttpStatus.OK);

    }
    
    
}
