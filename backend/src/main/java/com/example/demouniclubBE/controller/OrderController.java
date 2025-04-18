package com.example.demouniclubBE.controller;

import com.example.demouniclubBE.payload.request.InsertOrderDetailRequest;
import com.example.demouniclubBE.payload.request.OrderRequest;
import com.example.demouniclubBE.payload.response.BaseResponse;
import com.example.demouniclubBE.service.Imp.OrderServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @PostMapping
    public ResponseEntity<?> insertOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest) {

        boolean isSuccess = orderServiceImp.insertOrder(request, orderRequest);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOrder() {
        return new ResponseEntity<>(orderServiceImp.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/getDetail")
    public ResponseEntity<?> getDetailOrder(@RequestParam int id) {
        return new ResponseEntity<>(orderServiceImp.getDetailOrder(id), HttpStatus.OK);
    }

    @PatchMapping("/orderDetail")
    public ResponseEntity<?> updateOrderDetail(@RequestBody InsertOrderDetailRequest request) {
        boolean isSuccess = orderServiceImp.updateOrderDetail(request);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "update thành công" : "update thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateOrder(@RequestParam int id, @RequestParam String status) {
        boolean isSuccess = orderServiceImp.updateOrder(id, status);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "update thành công" : "update thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrder(@RequestParam int id) {
        boolean isSuccess = orderServiceImp.deleteOrder(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(isSuccess ? "delete thành công" : "delete thất bại");
        baseResponse.setStatusCode(200);
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
