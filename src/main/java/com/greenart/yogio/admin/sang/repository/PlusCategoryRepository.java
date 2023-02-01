package com.greenart.yogio.admin.sang.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.greenart.yogio.admin.sang.entity.PlusCategotyEntity;



public interface PlusCategoryRepository extends JpaRepository<PlusCategotyEntity,Long>{
    public Integer countByPcName(String pcName);

 
   
}
    

