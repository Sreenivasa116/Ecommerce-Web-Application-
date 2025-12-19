package com.application.mapper;

import com.application.entities.CartEntity;
import com.application.entities.CartItemEntity;

import com.example.model.*;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "cartItems",expression="java(cartEntity.getCartItems() != null ? " +
            "cartEntity.getCartItems().stream().map(this::toDtoCartItemResponse).toList():null)")
    CartResponse toDto(CartEntity cartEntity);
    @AfterMapping
    default void setTotalAmount(@MappingTarget CartResponse cart,CartEntity entity){
       cart.setTotalAmount( cart.getCartItems().stream()
                .map(cartItem ->
                        cartItem.getPrice()
                                .multiply(BigDecimal.valueOf(cartItem.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP));
    }

    CartEntity toEntity(CartResponse cart);

   @Mapping(target = "name",source="product.name")
   @Mapping(target = "price",source="product.price")
   @Mapping(target = "quantity",source="quantity")
    CartItemResponse toDtoCartItemResponse(CartItemEntity cartItemEntity);
   @AfterMapping
    default  void setTotalPrice(@MappingTarget CartItemResponse cartItemResponse,
                                CartItemEntity entity){
       cartItemResponse.setTotal(
               entity.getProduct().getPrice().multiply(
                       BigDecimal.valueOf(entity.getQuantity()).
                               setScale(2, RoundingMode.HALF_UP))
       );
   }
    CartItem toDto(CartItemEntity cartItemEntity);
    CartItemEntity toEntity(CartItem cartItem);


    //mapping data from request and adding to entity
    @Mapping(source= "quantity", target = "quantity")
    CartItemEntity addtoCartItemEntity(AddCartItemRequest addCartItemRequest);




}
