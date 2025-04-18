package com.example.demouniclubBE.payload.request;

import lombok.Data;

@Data
public class OrderRequest {

    private String fullName;
    private String address;
    private double total;
    private String phone;
    private String message;

}
