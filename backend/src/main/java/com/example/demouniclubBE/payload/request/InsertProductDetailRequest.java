package com.example.demouniclubBE.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertProductDetailRequest {
    private MultipartFile[] file;
    private int idProduct;
    private String size;
    private String color;
    private int quantity;
    private double price;
}
