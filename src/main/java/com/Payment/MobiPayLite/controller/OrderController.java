package com.Payment.MobiPayLite.controller;


import com.Payment.MobiPayLite.dto.OrderDto;
import com.Payment.MobiPayLite.dto.OrderResponseDto;
import com.Payment.MobiPayLite.dto.TransactionResponseDto;
import com.Payment.MobiPayLite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/pay")
    public ResponseEntity<OrderResponseDto> payMerchant(@RequestBody OrderDto dto)
    {
        return ResponseEntity.ok(orderService.makePayment(dto,"SUCCESS"));
    }

    @GetMapping("/history")
    public ResponseEntity<List<TransactionResponseDto>> getMyTransactions()
    {
        return ResponseEntity.ok(orderService.getTransactionHistory());
    }

}
