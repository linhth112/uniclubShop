package com.example.demouniclubBE.repository;


import com.example.demouniclubBE.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    ColorEntity findByName(String name);
}
