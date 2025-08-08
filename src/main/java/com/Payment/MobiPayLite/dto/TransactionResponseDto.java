package com.Payment.MobiPayLite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class TransactionResponseDto {
    private String MerchantName;
    private Double amount;
    private LocalDateTime orderTime;
}
