package com.Payment.MobiPayLite.repository;

import com.Payment.MobiPayLite.entity.UserEntity;
import com.Payment.MobiPayLite.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(UserEntity user);
}
