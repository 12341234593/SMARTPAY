package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.dto.OrderDto;
import com.Payment.MobiPayLite.dto.OrderResponseDto;
import com.Payment.MobiPayLite.dto.TransactionResponseDto;
import com.Payment.MobiPayLite.entity.MerchantEntity;
import com.Payment.MobiPayLite.entity.OrderEntity;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.entity.Wallet;
import com.Payment.MobiPayLite.repository.MerchantRepository;
import com.Payment.MobiPayLite.repository.OrderRepository;
import com.Payment.MobiPayLite.repository.UserRepository;
import com.Payment.MobiPayLite.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private MerchantRepository merchRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    public OrderResponseDto makePayment(OrderDto dto, String status)
    {
        UserEntity user=getLoggedInUser();
        Wallet wallet=walletRepo.findByUser(user).orElseThrow();
        System.out.println("UserId: " + user.getUser_name()); // Check if this is null


        if(wallet.getBalance()< dto.getAmount()){
            status="FAILED";
            throw new RuntimeException("Insufficient wallet Balance!");
        }


        if (dto.getMerchantId() == null) {
            status="FAILED";
            throw new IllegalArgumentException("Merchant ID must not be null");
        }
        MerchantEntity merchant=merchRepo.findById(dto.getMerchantId()).orElseThrow(()->new RuntimeException("Merchant not found"));
        System.out.println("MerchantId: " + merchant.getMerchantId()); // Check if this is null

//        Deduct amount logic
        wallet.setBalance(wallet.getBalance()-dto.getAmount());
        walletRepo.save(wallet);

//        Saving the Order
        OrderEntity order=new OrderEntity();
        order.setAmount(dto.getAmount());
        order.setOrderTime(LocalDateTime.now());
        order.setMerchant(merchant);
        order.setUser(user);
        orderRepo.save(order);

        return new OrderResponseDto("Payment Successful", wallet.getBalance(),merchant.getMerchantName(), dto.getAmount());
    }
    private UserEntity getLoggedInUser()
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email= auth.getName();
        return userRepo.findByEmail(email).orElseThrow();
    }


    public List<TransactionResponseDto> getTransactionHistory()
    {
        UserEntity user=getLoggedInUser();
        List<OrderEntity> orders =orderRepo.findByUser(user);

        return orders.stream().map(order-> new TransactionResponseDto(order.getMerchant().getMerchantName(),order.getAmount(),order.getOrderTime())).collect(Collectors.toList());
    }


}
