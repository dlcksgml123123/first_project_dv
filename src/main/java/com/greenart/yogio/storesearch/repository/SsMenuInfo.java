package com.greenart.yogio.storesearch.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.yogio.storesearch.entity.SsMenuInfoEntity;




@Repository
public interface SsMenuInfo extends JpaRepository<SsMenuInfoEntity, Long>{
   
    // List<SsMenuInfoEntity> findByMninameContains(String mniName);
    
    
}
