package com.example.demouniclubBE.service.Imp;

import com.example.demouniclubBE.dto.OrderDTO;
import com.example.demouniclubBE.dto.OrderDetailDTO;
import com.example.demouniclubBE.payload.request.CartRequest;
import com.example.demouniclubBE.payload.request.InsertOrderDetailRequest;
import com.example.demouniclubBE.payload.request.OrderRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrderServiceImp {
    boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest);
    List<OrderDTO> getAllOrder();
    List<OrderDetailDTO> getDetailOrder(int id);
    boolean updateOrderDetail(InsertOrderDetailRequest request);
    boolean updateOrder(int id, String status);
    boolean deleteOrder(int id);
}
