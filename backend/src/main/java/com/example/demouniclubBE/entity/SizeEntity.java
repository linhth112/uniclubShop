package com.example.demouniclubBE.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "size")
public class SizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "size")
    private List<ProductDetailEntity> ProductDetails;

    @OneToMany(mappedBy = "size")
    private List<CartEntity> carts;

    @OneToMany(mappedBy = "size")
    private List<OrderDetailEntity> orderDetails;
}
