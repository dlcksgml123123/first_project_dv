package com.greenart.yogio.admin.sang.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.yogio.admin.sang.entity.OptionLlistEntity;

public interface OptionListRepository extends JpaRepository<OptionLlistEntity, Long>{
    Page <OptionLlistEntity> findByMniSeq (Long mniSeq, Pageable pageable);
}
