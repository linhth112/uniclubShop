package com.example.demouniclubBE.repository;

import com.example.demouniclubBE.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
    OrdersEntity findById(int id);
}
