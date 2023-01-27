package com.greenart.yogio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.yogio.admin.repository.StoreInfoRepository;
import com.greenart.yogio.storeInfo.repository.SiMenuCateJoinRepository;
import com.greenart.yogio.storeInfo.repository.SiMenuPlusJoinRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoRepository;

@SpringBootTest
class YogioApplicationTests {
@Autowired SiMenuPlusJoinRepository siMenuPlusJoinRepository;
@Autowired SiStoreInfoRepository siStoreInfoRepository;
@Autowired SiMenuCateJoinRepository siMenuCateJoinRepository;
@Autowired StoreInfoRepository storeInfoRepository;
@Test
void findstore() {
	System.out.println(siMenuPlusJoinRepository.findAll());
}
@Test
void findstoreInfo() {
	System.out.println(siStoreInfoRepository.findAll());
}
@Test
void findmenu () {
	System.out.println(siMenuCateJoinRepository.findAll());
}
@Test
void findInfoStore () {
	System.out.println(storeInfoRepository.findAll());
}

}
