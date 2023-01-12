package com.greenart.yogio.lchwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.yogio.lchwork.repository.OiDeliveryInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMemberInfoRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiMenuCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiMenuInfoRepository;
import com.greenart.yogio.lchwork.repository.OiOrderInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPaymentInfoRepository;
import com.greenart.yogio.lchwork.repository.OiPlusCateJoinRepository;
import com.greenart.yogio.lchwork.repository.OiPlusCategoryRepository;
import com.greenart.yogio.lchwork.repository.OiPlusMenuChoiceRepository;
import com.greenart.yogio.lchwork.repository.OiPlusMenuRepository;
import com.greenart.yogio.lchwork.repository.OiStoreInfoRepository;

@Service
public class PaymentInfoService {
    @Autowired OiMemberInfoRepository miRepo;
    @Autowired OiPaymentInfoRepository piRepo;
    @Autowired OiStoreInfoRepository siRepo;
    @Autowired OiDeliveryInfoRepository diRepo;
    @Autowired OiMenuCategoryRepository mcRepo;
    @Autowired OiMenuCateJoinRepository mcjRepo;
    @Autowired OiMenuInfoRepository mniRepo;
    @Autowired OiPlusCategoryRepository pcRepo;
    @Autowired OiPlusCateJoinRepository pcjRepo;
    @Autowired OiPlusMenuRepository pmRepo;
    @Autowired OiPlusMenuChoiceRepository pmcRepo;
    @Autowired OiOrderInfoRepository oiRepo;
}
