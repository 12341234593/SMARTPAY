package com.Payment.MobiPayLite.entity;

import com.Payment.MobiPayLite.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name="User_Info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String password;
    @Column(name="Balance")
    private Double Balance=0.0;


    @Enumerated(EnumType.STRING)
    private Role role;  // USER, MERCHANT, ADMIN

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private Wallet wallet;


}
