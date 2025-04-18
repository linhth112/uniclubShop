package com.example.demouniclubBE.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertProductRequest {

    int idProduct;
    String name;
    double price;
    String description;
    String category;
}
