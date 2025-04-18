package com.example.demouniclubBE.payload.response;

import lombok.Data;

@Data
public class BaseResponse {
    private int statusCode;
    private String message;
    private Object data;
}
