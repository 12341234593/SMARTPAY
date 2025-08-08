package com.Payment.MobiPayLite.repository;

import com.Payment.MobiPayLite.entity.MerchantEntity;
import com.Payment.MobiPayLite.entity.OrderEntity;
import com.Payment.MobiPayLite.entity.Transaction;
import com.Payment.MobiPayLite.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findByUser(UserEntity user);
    //    Count all successful transaction for a user


    //    Get Latest 5 transaction for a user sorted by createdAt desc;
    List<OrderEntity> findTop5ByUserIdOrderByOrderTimeDesc(long userId);


    @Query("SELECT SUM(o.amount) FROM OrderEntity o WHERE o.status = 'SUCCESS'")
    Double getTotalSuccessAmount();


    long countByUserIdAndStatus(int id, String success);
}
