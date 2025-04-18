package com.example.demouniclubBE.entity.key;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailID implements Serializable {

    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "id_color")
    private int idColor;

    @Column(name = "id_size")
    private int idSize;

}
