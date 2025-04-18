package com.example.demouniclubBE.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailID implements Serializable {

    @Column(name = "id_order")
    private int idOrder;

    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "id_size")
    private int idSize;

    @Column(name = "id_color")
    private int idColor;

}
