package com.example.demouniclubBE.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "product")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "rate")
    private int rate;

    @Column(name = "description")
    private String description;

    @Column(name = "sku")
    private String sku;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> productImages;

    @OneToMany(mappedBy = "product")
    private List<ProductDetailEntity> productDetails;

    @OneToMany(mappedBy = "product")
    private List<CartEntity> carts;

    @OneToMany(mappedBy = "product")
    private List<OrderDetailEntity> orderDetails;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity category;
}
