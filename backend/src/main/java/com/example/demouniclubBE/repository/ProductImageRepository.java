package com.example.demouniclubBE.repository;

import com.example.demouniclubBE.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {
    List<ProductImageEntity> findByProductIdAndColorId(int idProduct, int idColor);
    ProductImageEntity findByProductIdAndNameAndColorId(int idProduct, String name, int idColor);

}
