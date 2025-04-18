package com.example.demouniclubBE.service.Imp;

import com.example.demouniclubBE.dto.ProductDTO;
import com.example.demouniclubBE.dto.SingleProductDTO;
import com.example.demouniclubBE.entity.ProductEntity;
import com.example.demouniclubBE.payload.request.InsertProductDetailRequest;
import com.example.demouniclubBE.payload.request.InsertProductRequest;

import java.util.List;

public interface ProductServiceImp {
    boolean insertProduct(InsertProductRequest request);
    List<ProductDTO> getAll();
    SingleProductDTO getSingleProduct(int id);
    List<ProductDTO> getProductDetail(int id);
    boolean insertProductDetail(InsertProductDetailRequest request);
    boolean updateProduct(InsertProductRequest request);
    boolean deleteProduct(int id);
}
