package com.config.homework.controller;

import com.config.homework.model.dto.request.OrderProductRequest;
import com.config.homework.model.dto.response.OrderResponse;
import com.config.homework.model.enumaration.EStatus;
import com.config.homework.service.OrderService;
import com.config.homework.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{customerId}")
    @Operation(summary = "Create new order.")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody List<OrderProductRequest> orderProductRequest,
            @PathVariable UUID customerId
    ) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("A new order is created successfully.")
                .payload(orderService.createOrder(customerId,orderProductRequest))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get all order by customer id.")
    public ResponseEntity<ApiResponse<Set<OrderResponse>>> getAllOrders(@PathVariable UUID customerId) {
        ApiResponse<Set<OrderResponse>> response = ApiResponse.<Set<OrderResponse>>builder()
                .message("Get all orders with customer id ("+customerId+") successfully.")
                .payload(orderService.getAllOrdersByCustomerId(customerId))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/status")
    @Operation(summary = "Update status order.")
    public ResponseEntity<ApiResponse<OrderResponse>> updateStatus(
            @RequestParam EStatus status,
            @RequestParam UUID orderId
    ){
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Successfully update the status of order to "+status.name()+".")
                .payload(orderService.updateOrderById(orderId,status))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by id.")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable UUID orderId) {
        ApiResponse<OrderResponse> response = ApiResponse.<OrderResponse>builder()
                .message("Get order id ("+orderId+") successfully.")
                .payload(orderService.getOrderById(orderId))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
