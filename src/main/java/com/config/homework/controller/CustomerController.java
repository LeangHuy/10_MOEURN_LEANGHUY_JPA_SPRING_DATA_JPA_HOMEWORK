package com.config.homework.controller;

import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerOrderResponse;
import com.config.homework.model.dto.response.CustomerResponse;
import com.config.homework.service.CustomerService;
import com.config.homework.service.serviceImpl.CustomerServiceImpl;
import com.config.homework.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerServiceImpl customerServiceImpl;

    @PostMapping
    @Operation(summary = "Create customer.")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CustomerRequest customerRequest
    ) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("A new customer is inserted successfully.")
                .payload(customerService.createCustomer(customerRequest))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all customers.")
    public ResponseEntity<ApiResponse<List<CustomerOrderResponse>>> getAllCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection
    ) {
        ApiResponse<List<CustomerOrderResponse>> response = ApiResponse.<List<CustomerOrderResponse>>builder()
                .message("Get all customers successfully.")
                .payload(customerService.getAllCustomers(size, page, sortBy, sortDirection))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    @Operation(summary = "Get customer by id.")
    public ResponseEntity<ApiResponse<CustomerOrderResponse>> getCustomer(@PathVariable("id") UUID customerId) {
        ApiResponse<CustomerOrderResponse> response = ApiResponse.<CustomerOrderResponse>builder()
                .message("Get customer id ("+customerId+") successfully.")
                .payload(customerService.getCustomerById(customerId))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer by id.")
    public ResponseEntity<ApiResponse<CustomerOrderResponse>> updateCustomer(
            @PathVariable("id") UUID id,
            @Valid @RequestBody CustomerRequest customerRequest
    ) {
        ApiResponse<CustomerOrderResponse> response = ApiResponse.<CustomerOrderResponse>builder()
                .message("Customer id ("+id+") is updated successfully.")
                .payload(customerService.updateCustomerById(id,customerRequest))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by id.")
    public ResponseEntity<ApiResponse<CustomerResponse>> deleteCustomer(@PathVariable("id") UUID customerId) {
        customerServiceImpl.deleteCustomerById(customerId);
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("Customer id ("+customerId+") is deleted successfully.")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
