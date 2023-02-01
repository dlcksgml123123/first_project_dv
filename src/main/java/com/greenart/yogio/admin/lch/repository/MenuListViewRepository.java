package com.greenart.yogio.admin.lch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.admin.lch.entity.MenuListViewEntity;

@Repository
public interface MenuListViewRepository extends JpaRepository<MenuListViewEntity, Long>{
    public Page<MenuListViewEntity> findBySiSeq(Long siSeq, Pageable pageable);
}
