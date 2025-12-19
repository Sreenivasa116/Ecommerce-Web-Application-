package com.application.mapper;

import com.application.entities.OrderEntity;
import com.application.entities.OrderItemEntity;
import com.example.model.Order;
import com.example.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")

public interface OrderMapper {

    public Order toDto(OrderEntity order);
    public OrderEntity toEntity(Order order);

//    @Mapping(source="orderEntity.id" ,target="orderId")
//    @Mapping(source="orderEntity.orderDate" ,target = "orderDate")
//    @Mapping(source = "orderEntity.totalAmount",target="totalAmount")
//    @Mapping(source = "orderEntity.status",target="status")
    @Mapping(expression = "java(mapItems(orderEntity.getOrderItemEntityList()))",target="items")
    public Order toResponse(OrderEntity orderEntity);

    default List<OrderItem> mapItems(List<OrderItemEntity> items){
        if(items == null)
            return Collections.emptyList();
        return items.stream().map(
                item ->{
          OrderItem orderItem = new OrderItem();
          orderItem.setName(item.getProduct().getName());
          orderItem.setPrice(item.getPrice());
          orderItem.setQuantity(item.getQuantity());
          orderItem.setProductId(item.getProduct().getId());
          orderItem.setTotal(item.getTotal());
          return orderItem;
        }).toList();
    }
}
