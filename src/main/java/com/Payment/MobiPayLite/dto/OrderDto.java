package com.Payment.MobiPayLite.dto;

import lombok.Data;

@Data
public class OrderDto {

    private Long MerchantId;
    private double amount;
}
