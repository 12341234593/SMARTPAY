package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.dto.WalletDto;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.entity.Wallet;
import com.Payment.MobiPayLite.repository.UserRepository;
import com.Payment.MobiPayLite.repository.WalletRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    public String addMoney(WalletDto dto)
    {
        UserEntity user=getLoggedInUser();

        Wallet wallet=walletRepository.findByUser(user).orElse(new Wallet(null,0.0,user));
        wallet.setBalance(wallet.getBalance() + dto.getAmount());
        walletRepository.save(wallet);
        return  "₹"+ dto.getAmount()+"added successfully";
    }
    public double getBalance()
    {
        UserEntity user=getLoggedInUser();
        Wallet wallet=walletRepository.findByUser(user).orElseThrow(()->new RuntimeException("Wallet not found"));
        return wallet.getBalance();
    }
    private UserEntity getLoggedInUser()
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email= auth.getName();
        return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
    }
}
