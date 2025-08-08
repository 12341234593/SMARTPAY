package com.Payment.MobiPayLite.service;

import com.Payment.MobiPayLite.entity.Transaction;
import com.Payment.MobiPayLite.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void recordTransaction(Long userId,Double amount,String receiverEmail,String status)
    {
        Transaction txn=new Transaction();
        txn.setId(userId);
        txn.setAmount(amount);
        txn.setReceiverEmail(receiverEmail);
        txn.setStatus(status);
        txn.setTime(LocalDateTime.now());
        transactionRepository.save(txn);
    }
}
