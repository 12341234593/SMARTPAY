package com.Payment.MobiPayLite.controller;

import com.Payment.MobiPayLite.dto.WalletDto;
import com.Payment.MobiPayLite.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/add")
    public ResponseEntity<String> addMoney(@RequestBody WalletDto dto)
    {
        return ResponseEntity.ok(walletService.addMoney(dto));
    }

    @GetMapping("/balance")
    public  ResponseEntity<Double> getBalance()
    {
        return ResponseEntity.ok(walletService.getBalance());
    }

}
