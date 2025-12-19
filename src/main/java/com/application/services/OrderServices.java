package com.application.services;

import com.application.entities.CartEntity;
import com.application.entities.OrderEntity;
import com.application.entities.OrderItemEntity;
import com.application.entities.UserEntity;
import com.application.mapper.OrderItemMapper;
import com.application.mapper.OrderMapper;
import com.application.repositories.*;
import com.example.model.Order;
import com.example.model.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServices {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

//    public Order placeOrder(PlaceOrderRequest placeOrderRequest){
//
//       Integer userId = placeOrderRequest.getUserId();
//       UserEntity user = userRespository.findById(userId).orElseThrow(
//               ()-> new RuntimeException("No User found by Id "+userId));
//        CartEntity cart = cartRepository.findByUserId(userId)
//                .orElseThrow(()-> new RuntimeException("No cart exists by user id "+
//                        placeOrderRequest.getUserId()));
//        double total = cart.getCartItems().stream().mapToDouble(
//                item->item.getProduct().getPrice()*item.getQuantity()).sum();
//
//        OrderEntity order = new OrderEntity();
//        order.setOrderDate(java.time.LocalDateTime.now());
//        order.setTotalAmount(total);
//        order.setStatus("PLACED");
//        order.setUser(user);
//
//        List<OrderItemEntity> orderItemList = cart.getCartItems().stream()
//                .map(cartItem -> {
//                    OrderItemEntity oi = new OrderItemEntity();
//                    oi.setOrder(order); // link to parent
//                    oi.setProduct(cartItem.getProduct());
//                    oi.setQuantity(cartItem.getQuantity());
//                    oi.setPrice(cartItem.getProduct().getPrice());
//                    return oi;
//                })
//                .toList();
//        order.setOrderItemEntityList(orderItemList);
//       OrderEntity orderSaved=orderRepository.save(order);
//
//       cartRepository.delete(cart);
//
//        return orderMapper.toDto(orderSaved) ;
//    }
//
//    public List<Order> getAllOrdersByUser(Integer userId){
//
//        if(userId == null || userId < 1)
//          throw   new IllegalArgumentException("Invalid User Id "+userId);
//
//        UserEntity user = userRespository.findById(userId).orElseThrow(()->
//           new RuntimeException("No User Exists by Id "+userId));
//
//        List<OrderEntity> orderList = orderRepository.findByUserId(userId);
//
//        if (orderList == null || orderList.isEmpty())
//            return  Collections.emptyList();
//
//        return orderList.stream().map(orderMapper :: toResponse).toList();
//    }
//
//    public Order getOrderById(Integer orderId){
//
//        if(orderId == null || orderId <1)
//            throw new IllegalArgumentException("Invalid Order Id "+orderId);
//
//        OrderEntity order = orderRepository.findById(orderId).orElseThrow(
//                ()-> new RuntimeException("No Order By Id "+orderId));
//
//        return orderMapper.toDto(order);
//    }

}
