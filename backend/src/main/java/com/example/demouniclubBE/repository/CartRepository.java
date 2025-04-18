package com.example.demouniclubBE.repository;

import com.example.demouniclubBE.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    CartEntity findByUserIdAndProductIdAndSizeIdAndColorId(int idUser, int idProduct, int idSize, int idColor);
    List<CartEntity> findByUserId(int idUser);
    CartEntity findById(int id);
}
