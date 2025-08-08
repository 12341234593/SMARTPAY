package com.Payment.MobiPayLite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordResetToken {

    @Id
    private String token; // UUID

    private int userid;

    private LocalDateTime expiry;

}
