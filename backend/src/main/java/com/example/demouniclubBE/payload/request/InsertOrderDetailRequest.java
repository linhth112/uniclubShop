package com.example.demouniclubBE.payload.request;

import lombok.Data;

@Data
public class InsertOrderDetailRequest {
    private int idOrder;
    private int idProduct;
    private int idSize;
    private int idColor;
    private String status;
}
