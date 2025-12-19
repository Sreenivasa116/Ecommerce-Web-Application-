package com.application.controllers;

import com.application.services.CartServices;
import com.example.api.CartApi;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController implements CartApi {
    @Autowired
    private CartServices cartServices;

    @Override
    public ResponseEntity<CartItemResponse> addItemToCart(AddCartItemRequest addCartItemRequest) {
        CartItemResponse ci = cartServices.addItemToCart(addCartItemRequest);
        if(ci == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(ci);
    }

    @Override
    public ResponseEntity<CartResponse> getCartByUserId(Integer userId) {
        CartResponse cart = cartServices.getCartByUserId(userId);
        if(cart != null)
            return ResponseEntity.status(HttpStatus.OK).body(cart);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity<CartItemResponse> updateCartItemQuantity
            (Integer itemId, UpdateCartItemQuantityRequest updateCartItemQuantityRequest) {
        CartItemResponse cartItem = cartServices.updateCartItemQuantity(itemId, updateCartItemQuantityRequest);
        if(cartItem == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(cartItem);
    }

    @Override
    public ResponseEntity<String> clearCartByUserId(Integer userId) {
        String result = cartServices.clearCartByUserId(userId);
        if(result == null || result.isEmpty() || result.equals("Failed to clear cart."))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Override
    public ResponseEntity<String> removeCartItem(Integer itemId) {
        String result = cartServices.removeCartItem(itemId);
        if(result == null || result.isEmpty() || result.equals("Failed to remove cart item"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
