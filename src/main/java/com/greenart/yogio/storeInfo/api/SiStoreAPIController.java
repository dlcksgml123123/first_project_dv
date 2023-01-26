package com.greenart.yogio.storeInfo.api;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.internal.lang.annotation.ajcDeclareParents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greenart.yogio.storeInfo.entity.SiStoreInfoEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreInfoMainMenuEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreInfoPlusMenuEntity;
import com.greenart.yogio.storeInfo.entity.SiDeliveryInfoEntity;
import com.greenart.yogio.storeInfo.entity.SiMenuPlusJoinEntity;
import com.greenart.yogio.storeInfo.entity.SiStoreInfoDetailEntity;
import com.greenart.yogio.storeInfo.repository.SiDeliveryInfoRepository;
import com.greenart.yogio.storeInfo.repository.SiMenuPlusJoinRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoDetailRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoListRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoMainMenuRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoPlusMenuRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoReviewRepository;
import com.greenart.yogio.storeInfo.service.SIStoreInfoMenuService;
import com.greenart.yogio.storeInfo.service.SiStoreInfoDetailService;
import com.greenart.yogio.storeInfo.service.SiStoreInfoListService;
import com.greenart.yogio.storeInfo.service.SiStoreInfoReviewService;
import com.greenart.yogio.storeInfo.service.SiStoreInfoService;
import com.greenart.yogio.storeInfo.vo.SiMenuListVO;
import com.greenart.yogio.storeInfo.vo.SiStoreInfoVO;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SiStoreAPIController {
    @Autowired SiDeliveryInfoRepository siDeliveryInfoRepository;
    @Autowired SiStoreInfoMainMenuRepository siStoreInfoMainMenuRepository;
    @Autowired SiStoreInfoPlusMenuRepository siStoreInfoPlusMenuRepository;
    @Autowired SiStoreInfoDetailService siStoreInfoDetailService;
    @Autowired SIStoreInfoMenuService siStoreInfoMenuService;
    @Autowired SiMenuPlusJoinRepository siMenuPlusJoinRepository;
    @Autowired SiStoreInfoReviewService siStoreInfoReviewService;
    @Autowired SiStoreInfoListService siStoreInfoListService;
    @Autowired SiStoreInfoService sService;
    @Autowired SiStoreInfoDetailRepository vrRepo;
    @Autowired SiStoreInfoListRepository silRepo;
    @Autowired SiStoreInfoReviewRepository sirRepo;
    @Autowired SiStoreInfoRepository siStoreInfoRepository;
    @Value("${file.image.store}") String store_img_path;
    @Value("${file.image.menu}") String menu_img_path;

    @PutMapping("/store/add") 
    public ResponseEntity<Object> putStoreInfo(SiStoreInfoVO data ,@RequestPart MultipartFile file) {
        Map<String, Object>  map = new LinkedHashMap<String, Object>();
        Path folderLocation =  Paths.get(store_img_path);
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
      data.setSiUri(filename);
      data.setSiFileName(saveFilename);

      SiDeliveryInfoEntity deliveryEntity = new SiDeliveryInfoEntity(null, data.getDiDistance()
      ,data.getDiDeliveryPrice(), data.getDiTime());
      siDeliveryInfoRepository.save(deliveryEntity);

      data.setSiDiSeq(deliveryEntity.getDiSeq());
      SiStoreInfoEntity storeEntity = new SiStoreInfoEntity(null, data.getSiName(), data.getSiUri(),
      data.getSiMinOrderPrice(),data.getSiDiscountPrice(),data.getSiDiscountCondition(),data.getSiDiSeq(),data.getSiCleanInfo(),
      data.getSiFileName());
      siStoreInfoRepository.save(storeEntity);
      map.put("message", "가게등록이 완료됐습니다.");
      
      return new ResponseEntity<>(map,HttpStatus.OK);

    }
  //   @GetMapping("/store/detail/")
  //   public Map<String, Object> getStoreDetail(
  //   @PageableDefault(size=5) Pageable pageable) {
  //   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  //   Page<SiStoreInfoDetailEntity> page = vrRepo.getStoreDetail(pageable);
  //   resultMap.put("total", page.getTotalPages());
  //   resultMap.put("curentpage",page.getNumber());
  //   resultMap.put("list",page);
  //   return resultMap;
  // }
  //   @GetMapping("/store/list/")
  //   public Map<String, Object> getStoreList(
  //   @PageableDefault(size=5) Pageable pageable, @RequestParam @Nullable String keyword) {
  //     if(keyword == null) {keyword = "";}
  //   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  //   List<SiStoreInfoListEntity> page = silRepo.getStoreList(keyword, pageable);
  //   resultMap.put("totalpage", silRepo.getStorePageCount(keyword));
  //   resultMap.put("totalCount",page.size());
  //   resultMap.put("list",page);
  //   return resultMap;
  // }
  @GetMapping("/store/list")
  public Map<String, Object> getStoreList(@RequestParam @Nullable String keyword, @PageableDefault (size=5,sort = "siSeq" ,direction = Sort.Direction.ASC) Pageable pageable) {
    if (keyword== null)  keyword = "";
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("result",siStoreInfoListService.getStoreList(keyword, pageable));
    return resultMap;
  }
    @GetMapping("/store/detail")
  public Map<String, Object> getStoreDetail(@RequestParam @Nullable Long siseq, @PageableDefault (size=5,sort = "siSeq" ,direction = Sort.Direction.ASC) Pageable pageable) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("result",siStoreInfoDetailService.getStoreDetail(siseq, pageable));
    return resultMap;
  }
  @GetMapping("/store/review")
  public Map<String, Object> getStoreReview(@RequestParam @Nullable Long siseq, @PageableDefault(size=5)Pageable pageable) {
    // if (seq == null ) seq = 1L;
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("result",siStoreInfoReviewService.getStoreReview(siseq, pageable));
    return resultMap;
  }
    @GetMapping("/store/menu")
  public Map<String, Object> getStoreMenu(@RequestParam @Nullable Long siseq,@PageableDefault (size=5,sort = "siSeq" ,direction = Sort.Direction.ASC) Pageable pageable) {
    // if (seq == null ) seq = 1L;
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    resultMap.put("result",siStoreInfoMenuService.getStoreMenu(siseq, pageable));
    return resultMap;
  }
  //   @GetMapping("/store/review/")
  //   public Map<String, Object> getStoreReview(
  //   @PageableDefault(size=5) Pageable pageable) {
  //   Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
  //   Page<SiStoreInfoReviewEntity> page = sirRepo.getStoreReview(pageable);
  //   resultMap.put("total", page.getTotalPages());
  //   resultMap.put("curentpage",page.getNumber());
  //   resultMap.put("list",page);
  //   return resultMap;
  // }
  @GetMapping("/images/{type}/{uri}") 
   public ResponseEntity<Resource> getImage ( @PathVariable String uri, 
            @PathVariable String type , HttpServletRequest request ) throws Exception { 
    Path folderLocation = null;
    String filename = null;
    if (type.equals("store")) {
      folderLocation = Paths.get(store_img_path);
      filename = sService.getFilenameByUri(uri);
    }
    else if (type.equals("menu")) {
      folderLocation = Paths.get(menu_img_path);
      filename = sService.getFilenameByUri(uri);
    }
    System.out.println(sService.getFilenameByUri(uri));
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
  //  int i = 0; i < page.size(); i++

    // @GetMapping("/menu/list")
    // public Map<String,Object> getMenuList(@PageableDefault(size=5) Pageable pageable) {
    //   Map<String,Object>  map = new LinkedHashMap<String, Object>();
    //   Page <SiMenuPlusJoinEntity> page = siMenuPlusJoinRepository.findAll(pageable);
    //   List<SiMenuListVO> menuList = new ArrayList<>();
    //   for(SiMenuPlusJoinEntity a : page.getContent()) {
    //     menuList.add(new SiMenuListVO(a));
    //     }

    //   System.out.println(menuList);
    //   map.put("status", true);
    //   map.put("list", menuList); 
    //   return map;
    // }

    @GetMapping("/menu/list")
    public Map<String, Object> getMenu(@RequestParam Long siSeq) {
      Map<String,Object> map = new LinkedHashMap<>();
      List<SiStoreInfoMainMenuEntity> mList = siStoreInfoMainMenuRepository.findBySiSeq(siSeq);
      List<Object> list = new ArrayList<Object>();
      for (int i=0; i<mList.size(); i ++){
        list.add(mList.get(i));
        List<SiStoreInfoPlusMenuEntity> pList = siStoreInfoPlusMenuRepository.findByMniSeq(mList.get(i).getMniSeq());
        list.add(pList);
      }
      map.put("list", list);
      return map;
        
    }
  
      
}
