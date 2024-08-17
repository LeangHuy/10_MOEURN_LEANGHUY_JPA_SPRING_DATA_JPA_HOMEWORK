package com.config.homework.service.serviceImpl;

import com.config.homework.exception.NotFoundException;
import com.config.homework.model.dto.request.OrderProductRequest;
import com.config.homework.model.dto.request.OrderRequest;
import com.config.homework.model.dto.response.OrderResponse;
import com.config.homework.model.dto.response.ProductResponse;
import com.config.homework.model.entities.Customer;
import com.config.homework.model.entities.Order;
import com.config.homework.model.entities.Product;
import com.config.homework.model.enumaration.EStatus;
import com.config.homework.repository.CustomerRepository;
import com.config.homework.repository.OrderProductRepository;
import com.config.homework.repository.OrderRepository;
import com.config.homework.repository.ProductRepository;
import com.config.homework.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderProductRepository orderProductRepository;


    @Override
    public OrderResponse createOrder(UUID customerId, List<OrderProductRequest> orderProductRequests) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + customerId));
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setTotalAmount(0);
        orderRequest.setStatus("PENDING");
        orderRequest.setCustomerId(customerId);
        Order order = orderRepository.save(orderRequest.toEntity(customer));
        double totalAmount = 0.0;
        Product product;
        Set<ProductResponse> productResponses = new HashSet<>();
        for (OrderProductRequest orderProductRequest : orderProductRequests) {
            product = productRepository.findById(orderProductRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("product not found with id: " + orderProductRequest.getProductId()));
            double totalPrice = product.getUnitPrice() * orderProductRequest.getQuantity();
            totalAmount += totalPrice;
            orderProductRequest.setQuantity(orderProductRequest.getQuantity());
            orderProductRepository.save(orderProductRequest.toEntity(order, product));
            productResponses.add(product.toResponse());
        }
        orderRequest.setTotalAmount(totalAmount);
        orderRequest.setStatus("PENDING");
        orderRequest.setCustomerId(customerId);
        orderRepository.save(orderRequest.toEntity(order.getId(), customer));
        return order.toResponse(productResponses);
    }

    @Override
    public Set<OrderResponse> getAllOrdersByCustomerId(UUID customerId) {
        Set<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            throw new NotFoundException("Customer id: " + customerId+" doesn't order yet.");
        }
        Set<OrderResponse> orderResponses = new HashSet<>();
        Product product;
        for (Order order : orders) {
            Set<ProductResponse> productResponses = new HashSet<>();
            List<UUID> productIds = orderProductRepository.findByOrderId(order.getId());
            for (UUID productId : productIds) {
                product = productRepository.findById(productId)
                        .orElseThrow(() -> new NotFoundException("product not found with id: " + productId));
                productResponses.add(product.toResponse());
            }
            OrderResponse orderResponse = order.toResponse(productResponses);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Override
    public OrderResponse updateOrderById(UUID orderId, EStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
        Set<ProductResponse> productResponses = new HashSet<>();
        Product product;
        List<UUID> productIds = orderProductRepository.findByOrderId(order.getId());
        for (UUID productId : productIds) {
            product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("product not found with id: " + productId));
            productResponses.add(product.toResponse());
        }
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setStatus(status.name());

        return orderRepository.save(orderRequest.toEntity(orderId,order.getCustomer())).toResponse(productResponses);
    }

    @Override
    public OrderResponse getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
        Set<ProductResponse> productResponses = new HashSet<>();
        Product product;
        List<UUID> productIds = orderProductRepository.findByOrderId(order.getId());
        for (UUID productId : productIds) {
            product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("product not found with id: " + productId));
            productResponses.add(product.toResponse());
        }
        return order.toResponse(productResponses);
    }
}
