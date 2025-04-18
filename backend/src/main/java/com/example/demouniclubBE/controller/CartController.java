package com.example.demouniclubBE.controller;

import com.example.demouniclubBE.dto.ProductDTO;
import com.example.demouniclubBE.payload.request.CartRequest;
import com.example.demouniclubBE.payload.response.BaseResponse;
import com.example.demouniclubBE.service.CartService;
import com.example.demouniclubBE.service.Imp.CartServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImp cartServiceImp;

    @PostMapping
    public ResponseEntity<?> insertCart(HttpServletRequest request, @RequestBody CartRequest cartRequest) {
        boolean isSuccess = cartServiceImp.insertCart(request, cartRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCart(HttpServletRequest request) {
        List<ProductDTO> listCart = cartServiceImp.getCart(request);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(listCart);
        baseResponse.setMessage("");
        baseResponse.setStatusCode(200);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(HttpServletRequest request,@RequestBody CartRequest cartRequest) {
        boolean isSuccess = cartServiceImp.updateCart(request,cartRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "update thành công" : "update thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCart(@RequestParam int id) {
        boolean isSuccess = cartServiceImp.deleteCart(id);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(isSuccess ? "delete thành công" : "delete thất bại");
        baseResponse.setStatusCode(200);
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
