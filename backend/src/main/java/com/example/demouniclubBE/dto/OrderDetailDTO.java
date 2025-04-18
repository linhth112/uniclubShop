package com.example.demouniclubBE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private int idOrder;
    private int idProduct;
    private int idSize;
    private int idColor;
    private String productName;
    private String size;
    private String color;
    private int quantity;
    private double price;
    private String status;
}
