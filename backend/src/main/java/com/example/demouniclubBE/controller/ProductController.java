package com.example.demouniclubBE.controller;

import com.example.demouniclubBE.dto.ProductDTO;
import com.example.demouniclubBE.dto.SingleProductDTO;
import com.example.demouniclubBE.payload.request.InsertProductDetailRequest;
import com.example.demouniclubBE.payload.request.InsertProductRequest;
import com.example.demouniclubBE.payload.response.BaseResponse;
import com.example.demouniclubBE.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping
    public ResponseEntity<?> getAllProduct() {

        return new ResponseEntity<>(productServiceImp.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-single-product")
    public ResponseEntity<?> getSingleProduct(@RequestParam int id) {
        SingleProductDTO singleProductDTO = productServiceImp.getSingleProduct(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("");
        baseResponse.setStatusCode(200);
        baseResponse.setData(singleProductDTO);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/get-product-detail")
    public ResponseEntity<?> getProductDetail(@RequestParam int id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setData(productServiceImp.getProductDetail(id));
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody InsertProductRequest request) {
        boolean isSuccess = productServiceImp.insertProduct(request);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/detail")
    public ResponseEntity<?> insertProductDetail(@ModelAttribute InsertProductDetailRequest request) {
        boolean isSuccess = productServiceImp.insertProductDetail(request);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@RequestBody InsertProductRequest request) {
        boolean isSuccess = productServiceImp.updateProduct(request);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Update thành công" : "Update thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam int id) {
        boolean isSuccess = productServiceImp.deleteProduct(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
