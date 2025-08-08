package com.Payment.MobiPayLite.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
private String token;
private String newPassword;
}
