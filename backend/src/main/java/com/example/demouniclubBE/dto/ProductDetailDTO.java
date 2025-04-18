package com.example.demouniclubBE.dto;

import lombok.Data;

@Data
public class ProductDetailDTO {
    private int idProduct;
    private int idSize;
    private int idColor;
    private int quantity;
    private double price;
}
