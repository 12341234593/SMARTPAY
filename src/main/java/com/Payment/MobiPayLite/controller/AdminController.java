package com.Payment.MobiPayLite.controller;

import com.Payment.MobiPayLite.dto.MerchantDto;
import com.Payment.MobiPayLite.entity.MerchantEntity;
import com.Payment.MobiPayLite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/merchant")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<String> addMerchant(@RequestBody MerchantDto dto)
    {
        return ResponseEntity.ok(adminService.addMerchant(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MerchantEntity>>  getAllMerchants()
    {
        return ResponseEntity.ok(adminService.getAllMerchants());
    }
}
