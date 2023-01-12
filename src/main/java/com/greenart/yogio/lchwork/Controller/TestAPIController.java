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

import com.greenart.yogio.lchwork.entity.BookEntity;
import com.greenart.yogio.lchwork.entity.BookWriterEntity;
import com.greenart.yogio.lchwork.entity.PublisherEntity;
import com.greenart.yogio.lchwork.entity.WriterEntity;
import com.greenart.yogio.lchwork.repository.BookRepository;
import com.greenart.yogio.lchwork.repository.BookWriterRepository;
import com.greenart.yogio.lchwork.repository.PublisherRepository;
import com.greenart.yogio.lchwork.repository.WriterRepository;
import com.greenart.yogio.lchwork.vo.TestBookVO;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/test")
public class TestAPIController {
    @Autowired BookRepository bRepo;
    @Autowired WriterRepository wRepo;
    @Autowired PublisherRepository pRepo;
    @Autowired BookWriterRepository bwRepo;
    
    @PutMapping("/add")
    public Map<String, Object> putTestAdd(TestBookVO data, @RequestPart @Nullable MultipartFile file) { // 파일과 함께 전송
    // public Map<String, Object> putTestAdd(@RequestBody TestBookVO data) { // 기존
        System.out.println(file.getOriginalFilename());
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        WriterEntity wEntity = wRepo.findByWriterName(data.getWriter_name());
        if(wEntity == null) {
            wEntity = new WriterEntity(null, data.getWriter_name());
            wRepo.save(wEntity);
        }
        PublisherEntity pEntity = pRepo.findByPubName(data.getPub_name()) ;
        if(pEntity == null) {
            pEntity = new PublisherEntity(null, data.getPub_name());
            pRepo.save(pEntity);
        }
        BookEntity bEntity = new BookEntity(null, data.getBook_name(), pEntity.getPubSeq());
        bRepo.save(bEntity);
        BookWriterEntity bwEntity = new BookWriterEntity(null, bEntity.getBookSeq(), wEntity.getWriterSeq());
        bwRepo.save(bwEntity);

        map.put("wEntity", wEntity);
        map.put("pEntity", pEntity);
        map.put("bEntity", bEntity);
        // map.put("bwEntity", bwEntity);

        return map;
    }
}
