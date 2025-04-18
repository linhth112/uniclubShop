package com.example.demouniclubBE.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product_image")
@Data
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity product;

}
