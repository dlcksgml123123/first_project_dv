package com.greenart.yogio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.yogio.storeInfo.repository.SiMenuPlusJoinRepository;
import com.greenart.yogio.storeInfo.repository.SiStoreInfoRepository;

@SpringBootTest
class YogioApplicationTests {
@Autowired SiMenuPlusJoinRepository siMenuPlusJoinRepository;
@Autowired SiStoreInfoRepository siStoreInfoRepository;
@Test
void findstore() {
	System.out.println(siMenuPlusJoinRepository.findAll());
}
@Test
void findstor() {
	System.out.println(siStoreInfoRepository.findAll());
}


}
