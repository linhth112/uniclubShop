package com.example.demouniclubBE.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private int idCart;
    private int idProduct;
    private int idColor;
    private String colorName;
    private int idSize;
    private String sizeName;
    private String name;
    private double price;
    private List<String> images;
    private int quantity;
    private String description;
    private String sku;
    private String category;
    private int idCategory;
}
