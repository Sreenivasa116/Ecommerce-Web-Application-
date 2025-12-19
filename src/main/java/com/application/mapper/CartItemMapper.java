package com.application.mapper;

import com.application.entities.CartItemEntity;
import com.example.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper{

    CartItemEntity toEntity(CartItem cartItem);
    CartItem toDto(CartItemEntity cartItemEntity);
}
