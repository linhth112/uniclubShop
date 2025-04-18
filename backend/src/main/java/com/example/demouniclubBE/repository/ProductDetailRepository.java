package com.example.demouniclubBE.repository;

import com.example.demouniclubBE.entity.OrderDetailEntity;
import com.example.demouniclubBE.entity.ProductDetailEntity;
import com.example.demouniclubBE.entity.key.ProductDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, ProductDetailID> {
    List<ProductDetailEntity> findByProductId(int id);
    ProductDetailEntity findByProductIdAndColorIdAndSizeId(int idProduct, int idColor, int idSize);
}
