package com.Payment.MobiPayLite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDto {
private String message;
private double RemainingBalance;
private String MerchantName;
private double TransferAmount;

}
