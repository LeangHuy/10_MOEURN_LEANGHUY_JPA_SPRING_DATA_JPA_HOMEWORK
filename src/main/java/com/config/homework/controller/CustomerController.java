package com.config.homework.controller;

import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerResponse;
import com.config.homework.model.entities.Customer;
import com.config.homework.service.CustomerService;
import com.config.homework.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .message("A new customer is inserted successfully.")
                .payload(customerService.createCustomer(customerRequest))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
