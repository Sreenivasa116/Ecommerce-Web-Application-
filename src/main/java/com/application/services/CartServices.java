package com.application.services;


import com.application.entities.*;
import com.application.exception.*;
import com.application.mapper.CartMapper;
import com.application.repositories.CartItemRepository;
import com.application.repositories.CartRepository;
import com.application.repositories.ProductRepository;
import com.application.repositories.UserRespository;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Service
public class CartServices {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public CartItemResponse addItemToCart(AddCartItemRequest addItemRequest){

        Integer userId = addItemRequest.getUserId();
        UserEntity user  = userRespository.findById(userId).
                orElseThrow(()-> {
                    throw new UserNotFoundException
                ("User not found with id : '"+userId+"'.");
                });
        CartEntity cart = user.getCart();

        if(cart == null ) {

          cart = new CartEntity();
          cart.setCreatedAt(java.time.LocalDateTime.now());
          cart.setUser(user);
          cart = cartRepository.save(cart);
          user.setCart(cart);
          userRespository.save(user);
        }

        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }

        // CHECKING THE PRODUCT IS IN DATABASE
        ProductEntity product = productRepository.findById(addItemRequest.getProductId())
                .orElseThrow(() -> {
                    throw new ProductNotFoundException
                            ("No product exists by Product Id : "+addItemRequest.getProductId());
                });

        CartItemEntity item = null;
        if (cart.getCartItems() != null) {
            item = cart.getCartItems().stream()
                    .filter(cartItem ->
                            cartItem.getProduct().getId().equals(product.getId())
                    )
                    .findFirst()
                    .orElse(null);
        }
        if(item != null)
            item.setQuantity(item.getQuantity()+ addItemRequest.getQuantity());
        else{
            item = cartMapper.addtoCartItemEntity(addItemRequest);
            item.setProduct(product);
            item.setCart(cart);
            item.setProductName(product.getName());
            item.setPrice(product.getPrice());
            cart.getCartItems().add(item);
        }

        item = cartItemRepository.save(item);
      return cartMapper.toDtoCartItemResponse(item);
    }

    public CartResponse getCartByUserId(Integer userId){

      CartEntity cart = cartRepository.findByUser_Id(userId).orElseThrow(
              () -> {throw new CartNotFoundException("No Cart exists by user Id :' "+userId+"'. ");} );
        CartResponse cartResponse = null;
        if(cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            cartResponse = new CartResponse();
            cartResponse.setTotalAmount(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
            cartResponse.setCartItems(new ArrayList<>());
        }else{
            cartResponse = cartMapper.toDto(cart);
        }
      return cartResponse;

    }

    @Transactional
    public CartItemResponse updateCartItemQuantity
            (Integer itemId, UpdateCartItemQuantityRequest updateCartItemQuantityRequest){

        Integer quantity = updateCartItemQuantityRequest.getQuantity();
        CartItemEntity item = cartItemRepository.findById(itemId).orElseThrow(
                () -> {throw new CartItemNotFoundException("No Cart Item Exists on id");});
        if(quantity == null || quantity <= 0)
            throw new InsufficientProductQuantityException("Send Proper Quantity of item");
        if(item.getProduct().getStockQuantity() < quantity)
            throw new InsufficientProductQuantityException("Insufficient Product Quantity");
        item.setQuantity(quantity);
        return cartMapper.toDtoCartItemResponse(item);
    }

    @Transactional
    public String clearCartByUserId(Integer userId){

        CartEntity cart = cartRepository.findByUser_Id(userId).orElseThrow(
                ()->{ throw new CartNotFoundException("No cart exist by id "+userId);});

        if(cart.getCartItems().isEmpty() ){
            return  "Cart is Empty";
        }else{
            cartItemRepository.deleteAllByCart_Id(cart.getId());
//            cartRepository.flush();
            return  "Cleared Cart successfully";
        }

    }

    @Transactional
    public String removeCartItem(Integer itemId) {

        CartItemEntity cartItem = cartItemRepository.findById(itemId).orElseThrow(()->
                new CartItemNotFoundException("No cart item exists by ID : '"+itemId+"'."));
        cartItem.getCart().getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cartItemRepository.flush();
        return "Deleted cart item by id "+itemId;
    }

}
