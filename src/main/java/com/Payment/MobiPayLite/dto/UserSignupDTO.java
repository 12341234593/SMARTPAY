package com.Payment.MobiPayLite.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSignupDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    private String role; //USER,MERCHANT,ADMIN

}
