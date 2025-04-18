package com.example.demouniclubBE.service;

import com.example.demouniclubBE.dto.ProductDTO;
import com.example.demouniclubBE.entity.*;
import com.example.demouniclubBE.payload.request.CartRequest;
import com.example.demouniclubBE.repository.CartRepository;
import com.example.demouniclubBE.repository.ProductImageRepository;
import com.example.demouniclubBE.service.Imp.CartServiceImp;
import com.example.demouniclubBE.utils.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public boolean insertCart(HttpServletRequest request, CartRequest cartRequest) {

        try {
            int idUser = Integer.parseInt(jwtHelper.getIdUserFromToken(request));

            CartEntity cartEntity = new CartEntity();

            UserEntity userEntity = new UserEntity();
            userEntity.setId(idUser);
            cartEntity.setUser(userEntity);

            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setId(cartRequest.getIdSize());
            cartEntity.setSize(sizeEntity);

            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(cartRequest.getIdProduct());
            cartEntity.setProduct(productEntity);

            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(cartRequest.getIdColor());
            cartEntity.setColor(colorEntity);

            cartEntity.setQuantity(cartRequest.getQuantity());

            if (cartRepository.findByUserIdAndProductIdAndSizeIdAndColorId(idUser, cartRequest.getIdProduct(), cartRequest.getIdSize(), cartRequest.getIdColor()) != null) {
                CartEntity oldCartEntity = cartRepository.findByUserIdAndProductIdAndSizeIdAndColorId(idUser, cartRequest.getIdProduct(), cartRequest.getIdSize(), cartRequest.getIdColor());
                int oldQuantity = oldCartEntity.getQuantity();
                int newQuantity = oldQuantity + cartRequest.getQuantity();
                oldCartEntity.setQuantity(newQuantity);
                cartRepository.save(oldCartEntity);
            } else {
                cartRepository.save(cartEntity);
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<ProductDTO> getCart(HttpServletRequest request) {

            int idUser = Integer.parseInt(jwtHelper.getIdUserFromToken(request));
            List<CartEntity> cartEntity = cartRepository.findByUserId(idUser);

            List<ProductDTO> listCart = new ArrayList<>();
            cartEntity.forEach(item -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(item.getProduct().getName());
                productDTO.setPrice(item.getProduct().getPrice());
                productDTO.setIdProduct(item.getProduct().getId());
                productDTO.setQuantity(item.getQuantity());
                productDTO.setColorName(item.getColor().getName());
                productDTO.setSizeName(item.getSize().getName());
                productDTO.setIdSize(item.getSize().getId());
                productDTO.setIdColor(item.getColor().getId());
                productDTO.setIdCart(item.getId());

                List<String> listImage = new ArrayList<>();
                productImageRepository.findByProductIdAndColorId(item.getProduct().getId(), item.getColor().getId()).forEach(itemImg ->{
                    listImage.add("http://localhost:8080/file/" + itemImg.getName());
                });

                productDTO.setImages(listImage);
                listCart.add(productDTO);

            });

            return listCart;

    }

    @Override
    public boolean updateCart(HttpServletRequest request, CartRequest cartRequest) {

        try {
            int idUser = Integer.parseInt(jwtHelper.getIdUserFromToken(request));
//            System.out.println("User ID: " + idUser);
            int idProduct = cartRequest.getIdProduct();
            int idSize = cartRequest.getIdSize();
            int idColor = cartRequest.getIdColor();
//            System.out.println(idProduct + "-" +idSize + "-" + idColor );
            CartEntity cartEntity = cartRepository.findByUserIdAndProductIdAndSizeIdAndColorId(idUser, idProduct, idSize, idColor);

            if (cartEntity == null) {
                System.out.println("Cart item not found for the given parameters.");
                return false;
            }

            cartEntity.setQuantity(cartRequest.getQuantity());
            cartRepository.save(cartEntity);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCart(int id) {
        try {
            CartEntity cartEntity = cartRepository.findById(id);
            if (cartEntity != null) {
                cartRepository.deleteById(id);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
