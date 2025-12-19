package com.application.controllers;

import com.application.services.OrderServices;
import com.example.api.OrdersApi;
import com.example.model.Order;
import com.example.model.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class OrdersController implements OrdersApi {

    @Autowired
    private OrderServices orderServices;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return OrdersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<List<Order>> getAllOrdersByUser(Integer userId) {
        return OrdersApi.super.getAllOrdersByUser(userId);
    }

    @Override
    public ResponseEntity<Order> getOrderById(Integer orderId) {
        return OrdersApi.super.getOrderById(orderId);
    }

    @Override
    public ResponseEntity<Order> placeOrder(PlaceOrderRequest placeOrderRequest) {
        return OrdersApi.super.placeOrder(placeOrderRequest);
    }

    //    @Override
//    public ResponseEntity<Order> placeOrder(PlaceOrderRequest placeOrderRequest) {
//        Order order = orderServices.placeOrder(placeOrderRequest);
//        return ResponseEntity.ok(order);
//    }
//
//    @Override
//    public ResponseEntity<List<Order>> getAllOrdersByUser(Integer userId) {
//        List<Order> orders = orderServices.getAllOrdersByUser(userId);
//        return ResponseEntity.ok(orders);
//    }
//
//    @Override
//    public ResponseEntity<Order> getOrderById(Integer orderId) {
//        return ResponseEntity.ok(orderServices.getOrderById(orderId));
//    }
}
