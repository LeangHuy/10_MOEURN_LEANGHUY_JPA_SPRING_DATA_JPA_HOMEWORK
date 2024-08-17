package com.config.homework.service;

import com.config.homework.model.dto.request.OrderProductRequest;
import com.config.homework.model.dto.response.OrderResponse;
import com.config.homework.model.entities.Order;
import com.config.homework.model.enumaration.EStatus;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(UUID customerId, List<OrderProductRequest> orderProductRequest);
    Set<OrderResponse> getAllOrdersByCustomerId(UUID customerId);
    OrderResponse updateOrderById(UUID orderId, EStatus status);
    OrderResponse getOrderById(UUID orderId);
}
