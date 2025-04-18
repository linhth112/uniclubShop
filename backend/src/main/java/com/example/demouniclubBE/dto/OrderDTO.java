package com.example.demouniclubBE.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private int idOder;
    private int idUser;
    private String fullName;
    private String address;
    private String phone;
    private double total;
    private String message;
    private String status;
}
