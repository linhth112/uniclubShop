package com.example.demouniclubBE.entity;

import com.example.demouniclubBE.entity.key.OrderDetailID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "order_detail")
public class OrderDetailEntity {

    @EmbeddedId
    private OrderDetailID id;

    @Column(name = "quatity")
    private int quatity;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_order", updatable = false, insertable = false)
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name = "id_product", updatable = false, insertable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "id_size", updatable = false, insertable = false)
    private SizeEntity size;

    @ManyToOne
    @JoinColumn(name = "id_color", updatable = false, insertable = false)
    private ColorEntity color;
}
