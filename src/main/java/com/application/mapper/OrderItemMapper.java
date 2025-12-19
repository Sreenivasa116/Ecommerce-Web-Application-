package com.application.mapper;

import com.application.entities.OrderItemEntity;
import com.example.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    public OrderItem toDto(OrderItemEntity orderItem);
    public  OrderItemEntity toEntity(OrderItem orderItem);
}
