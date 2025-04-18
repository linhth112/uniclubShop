package com.example.demouniclubBE.exception;

import com.example.demouniclubBE.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {

    @ExceptionHandler({RuntimeException.class, InsertProductException.class, SaveFileException.class})
    public ResponseEntity<?> handleException (Exception e) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(500);
        baseResponse.setData("");
        baseResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
