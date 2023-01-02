package com.greenart.yogio.member.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.member.entity.MemberInfoEntity;
import com.greenart.yogio.member.repository.MemberInfoRepository;

@Service
public class MemberService {
   @Autowired MemberInfoRepository m_repo;
   public Map<String, Object> addMember(MemberInfoEntity data) {
    Map<String ,Object> resultMap = new LinkedHashMap<String, Object>();

    return resultMap;
}
}