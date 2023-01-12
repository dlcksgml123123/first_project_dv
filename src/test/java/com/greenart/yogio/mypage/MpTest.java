package com.greenart.yogio.mypage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.yogio.mypage.store.entity.MpStoreInfoEntity;
import com.greenart.yogio.mypage.store.repository.MpStoreInfoRepository;

@SpringBootTest
public class MpTest {
  // @Test
  // void showMemberInfo() {

  // }

  @Autowired
  MpStoreInfoRepository sRepo;
  @Test
  void showstore(Long seq) {
    MpStoreInfoEntity store = new MpStoreInfoEntity();
    store=sRepo.findBySiSeq(seq);
    System.out.println(store);
  }
}
