package com.greenart.yogio.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.greenart.yogio.member.entity.MbMemberInfoEntity;
import com.greenart.yogio.member.repository.MbMemberInfoRepository;
import com.greenart.yogio.utils.MbAESAlgorithm;

@SpringBootTest
class MbYogioApplicationTests {

	@Autowired MbMemberInfoRepository mRepo;
	@Test
  @Transactional 
	void memberAccountAdd() { //회원가입테스트
		MbMemberInfoEntity account 
		= MbMemberInfoEntity
		.builder().miId("user11").miPwd("1234").miEmail("user010@naver.com").miPhone("010-1234-1234").miNickname("유저").miAddress("대구시 중구 삼덕동1가 4-4").miStatus(0).build();
		mRepo.save(account);
		System.out.println(account);
	}

	@Test
  @Transactional
	void testLogin() throws Exception{ //로그인테스트
		String id = "user17";
		String pwd = "92449244";
		MbMemberInfoEntity loginUser = mRepo.findTop1ByMiIdAndMiPwd(id, MbAESAlgorithm.Encrypt(pwd));
		assertNotEquals(loginUser,null); 
		}
		
	@Test
	void duplicatedTest() { //아이디 중복 테스트
		Integer cnt = mRepo.countByMiId("user12");
		assertEquals(cnt, 1);		
	}

	@Test
	@Transactional
	void deleteTest() { //삭제 테스트
		
	}
}	

