package com.example.demouniclubBE.repository;

import com.example.demouniclubBE.entity.OrderDetailEntity;
import com.example.demouniclubBE.entity.key.OrderDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, OrderDetailID> {
    List<OrderDetailEntity> findByOrderId(int id);
    OrderDetailEntity findByOrderIdAndProductIdAndSizeIdAndColorId(int idOrder, int idProduct, int idSize, int idColor);
}
