package com.greenart.yogio.admin.sang.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.greenart.yogio.admin.sang.entity.MenuOptionEntity;


public interface MenuOptionRepository extends JpaRepository<MenuOptionEntity,Long>{
    public Page<MenuOptionEntity> findByMniNameContains(String mniName, Pageable pageable);
    public Integer countByMniName(String mniName);
}
