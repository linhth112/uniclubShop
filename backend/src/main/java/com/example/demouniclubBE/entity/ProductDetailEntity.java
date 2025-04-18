package com.example.demouniclubBE.entity;

import com.example.demouniclubBE.entity.key.ProductDetailID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "product_detail")
public class ProductDetailEntity {

    @EmbeddedId
    private ProductDetailID id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_color", insertable = false, updatable = false)
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "id_size", insertable = false, updatable = false)
    private SizeEntity size;
}
