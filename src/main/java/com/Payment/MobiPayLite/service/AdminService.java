package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.dto.MerchantDto;
import com.Payment.MobiPayLite.entity.MerchantEntity;
import com.Payment.MobiPayLite.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private MerchantRepository merchantRepo;

    public String addMerchant(MerchantDto dto)
    {
        MerchantEntity merchant=new MerchantEntity();
        merchant.setMerchantName(dto.getMerchantName());
        merchant.setUpiId(dto.getUpiId());

        merchantRepo.save(merchant);

        return "Merchant Added Successfully";
    }
    public List<MerchantEntity> getAllMerchants()
    {
        return merchantRepo.findAll();
    }
}
