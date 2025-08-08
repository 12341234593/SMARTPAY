package com.Payment.MobiPayLite.repository;

import com.Payment.MobiPayLite.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<MerchantEntity,Long> {
    
}
