package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.entity.OrderEntity;
import com.Payment.MobiPayLite.entity.Transaction;
import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.entity.Wallet;
import com.Payment.MobiPayLite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashBoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WalletRepository walletRepository;

    public Map<String, Object>getUserDashBoard(long userid)
    {
        Map<String,Object> response=new HashMap<>();
        UserEntity user=userRepository.findById(userid).orElseThrow(()-> new RuntimeException("User Not Found"));
        Wallet wallet=walletRepository.findByUser(user).orElseThrow();
        response.put("userName", user.getUser_name());
        response.put("email",user.getEmail());
        response.put("WalletBalance",wallet.getBalance());

        long orderCount=orderRepository.countByUserIdAndStatus(user.getId(), "SUCCESS");
        response.put("total orders",orderCount);
        List<OrderEntity> recentTransactions = orderRepository.findTop5ByUserIdOrderByOrderTimeDesc(userid);
        response.put("recentTransactions", recentTransactions);

        return response;
    }
    public Map<String, Object> getAdminDashboard() {
        Map<String, Object> response = new HashMap<>();

        response.put("totalUsers", userRepository.count());
        response.put("totalMerchants", merchantRepository.count());
        response.put("totalTransactions", orderRepository.count());


        Double revenue = orderRepository.getTotalSuccessAmount();
        response.put("totalRevenue", revenue != null ? revenue : 0.0);

        return response;
    }
}
