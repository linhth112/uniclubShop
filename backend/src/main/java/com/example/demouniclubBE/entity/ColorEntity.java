package com.example.demouniclubBE.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "color")
public class ColorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    private List<ProductDetailEntity> ProductDetails;

    @OneToMany(mappedBy = "color")
    private List<CartEntity> carts;

    @OneToMany(mappedBy = "color")
    private List<OrderDetailEntity> orderDetails;

    @OneToMany(mappedBy = "color")
    private List<ProductImageEntity> productImages;
}
