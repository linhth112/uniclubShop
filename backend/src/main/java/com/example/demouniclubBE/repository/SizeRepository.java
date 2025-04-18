package com.example.demouniclubBE.repository;

import com.example.demouniclubBE.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
    SizeEntity findByName(String size);
}
