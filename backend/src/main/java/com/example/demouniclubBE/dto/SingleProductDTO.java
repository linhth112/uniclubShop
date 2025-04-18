package com.example.demouniclubBE.dto;

import lombok.Data;

import java.util.List;

@Data
public class SingleProductDTO {
    private String description;
    private String name;
    private double price;
    private List<SizeList> sizeList;
    private List<ColorList> colorList;
    private List<ImagesDTO> images;
    private List<ProductDetailDTO> productDetailS;
}
