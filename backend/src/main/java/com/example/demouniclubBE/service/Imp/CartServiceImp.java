package com.example.demouniclubBE.service.Imp;

import com.example.demouniclubBE.dto.ProductDTO;
import com.example.demouniclubBE.payload.request.CartRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CartServiceImp {
    boolean insertCart(HttpServletRequest request, CartRequest cartRequest);
    List<ProductDTO> getCart(HttpServletRequest request);
    boolean updateCart(HttpServletRequest request, CartRequest cartRequest);
    boolean deleteCart(int id);
}
