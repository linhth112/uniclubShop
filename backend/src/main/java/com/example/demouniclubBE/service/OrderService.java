package com.example.demouniclubBE.service;

import com.example.demouniclubBE.dto.OrderDTO;
import com.example.demouniclubBE.dto.OrderDetailDTO;
import com.example.demouniclubBE.entity.*;
import com.example.demouniclubBE.entity.key.OrderDetailID;
import com.example.demouniclubBE.payload.request.InsertOrderDetailRequest;
import com.example.demouniclubBE.payload.request.OrderRequest;
import com.example.demouniclubBE.repository.CartRepository;
import com.example.demouniclubBE.repository.OrderDetailRepository;
import com.example.demouniclubBE.repository.OrdersRepository;
import com.example.demouniclubBE.repository.ProductDetailRepository;
import com.example.demouniclubBE.service.Imp.OrderServiceImp;
import com.example.demouniclubBE.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public boolean insertOrder(HttpServletRequest request, OrderRequest orderRequest) {
        try {
            int idUser = Integer.parseInt(jwtHelper.getIdUserFromToken(request));

            OrdersEntity ordersEntity = new OrdersEntity();
            ordersEntity.setFullName(orderRequest.getFullName());
            ordersEntity.setAddress(orderRequest.getAddress());
            ordersEntity.setPhone(orderRequest.getPhone());
            ordersEntity.setTotal(orderRequest.getTotal());
            ordersEntity.setMessage(orderRequest.getMessage());
            ordersEntity.setStatus("open");
            UserEntity userEntity = new UserEntity();
            userEntity.setId(idUser);
            ordersEntity.setUser(userEntity);
            OrdersEntity ordersEntitySaved = ordersRepository.save(ordersEntity);

            List<CartEntity> cartEntityList = cartRepository.findByUserId(idUser);
            cartEntityList.forEach(item -> {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();

                OrderDetailID orderDetailID = new OrderDetailID();
                orderDetailID.setIdOrder(ordersEntitySaved.getId());
                orderDetailID.setIdProduct(item.getProduct().getId());
                orderDetailID.setIdSize(item.getSize().getId());
                orderDetailID.setIdColor(item.getColor().getId());

                orderDetailEntity.setId(orderDetailID);
                orderDetailEntity.setQuatity(item.getQuantity());
                orderDetailEntity.setPrice(item.getProduct().getPrice());
                orderDetailEntity.setStatus("prepare");

                orderDetailRepository.save(orderDetailEntity);
            });

            cartRepository.deleteAll(cartEntityList);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        ordersRepository.findAll().forEach(item -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setIdOder(item.getId());
            orderDTO.setIdUser(item.getUser().getId());
            orderDTO.setFullName(item.getFullName());
            orderDTO.setAddress(item.getAddress());
            orderDTO.setPhone(item.getPhone());
            orderDTO.setTotal(item.getTotal());
            orderDTO.setMessage(item.getMessage());
            orderDTO.setStatus(item.getStatus());

            orderDTOList.add(orderDTO);
        });

        return orderDTOList;
    }

    @Override
    public List<OrderDetailDTO> getDetailOrder(int id) {
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        orderDetailRepository.findByOrderId(id).forEach(item -> {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setIdOrder(item.getOrder().getId());
            orderDetailDTO.setIdProduct(item.getProduct().getId());
            orderDetailDTO.setIdSize(item.getSize().getId());
            orderDetailDTO.setIdColor(item.getColor().getId());
            orderDetailDTO.setProductName(item.getProduct().getName());
            orderDetailDTO.setColor(item.getColor().getName());
            orderDetailDTO.setSize(item.getSize().getName());
            orderDetailDTO.setPrice(item.getPrice());
            orderDetailDTO.setQuantity(item.getQuatity());
            orderDetailDTO.setStatus(item.getStatus());

            orderDetailDTOList.add(orderDetailDTO);
        });

        return orderDetailDTOList;
    }

    @Transactional
    @Override
    public boolean updateOrderDetail(InsertOrderDetailRequest request) {
        try {
            int idOrder = request.getIdOrder();
            int idProduct = request.getIdProduct();
            int idSize = request.getIdSize();
            int idColor = request.getIdColor();
            System.out.println(idOrder +"-"+ idProduct + "-" +idSize + "-" + idColor );
            OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderIdAndProductIdAndSizeIdAndColorId(idOrder,idProduct,idSize,idColor);
            if (orderDetailEntity == null) {
                System.out.println("OrderDetail item not found for the given parameters.");
                return false;
            }
            orderDetailEntity.setStatus(request.getStatus().toLowerCase());

            orderDetailRepository.save(orderDetailEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean updateOrder(int id, String status) {
        try {
            OrdersEntity ordersEntity = ordersRepository.findById(id);
            if (ordersEntity == null) {
                System.out.println("Orders item not found for the given parameters.");
                return false;
            }
            ordersEntity.setStatus(status);

            ordersRepository.save(ordersEntity);
            if (status.equalsIgnoreCase("cancel")) {
                orderDetailRepository.findByOrderId(id).forEach(item -> {
                    int idProduct = item.getProduct().getId();
                    int idSize = item.getSize().getId();
                    int idColor = item.getColor().getId();
                    int quantityOrder = item.getQuatity();
                    ProductDetailEntity productDetailEntity = productDetailRepository.findByProductIdAndColorIdAndSizeId(idProduct,idColor,idSize);
                    if (productDetailEntity != null) {
                        int quantityProductDetail = productDetailEntity.getQuantity();
                        int newQuantityProductDetail = quantityProductDetail - quantityOrder;
                        productDetailEntity.setQuantity(newQuantityProductDetail);
                        productDetailRepository.save(productDetailEntity);
                    };
                });

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteOrder(int id) {
        try {
            orderDetailRepository.deleteAll(orderDetailRepository.findByOrderId(id));
            ordersRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
