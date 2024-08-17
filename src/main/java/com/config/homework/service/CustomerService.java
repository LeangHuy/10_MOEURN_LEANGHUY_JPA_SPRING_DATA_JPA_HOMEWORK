package com.config.homework.service;

import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerOrderResponse;
import com.config.homework.model.dto.response.CustomerResponse;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerOrderResponse> getAllCustomers(int size, int page, String sortBy, Sort.Direction sortDirection);
    CustomerOrderResponse updateCustomerById(UUID customerId,CustomerRequest customerRequest);
    CustomerOrderResponse getCustomerById(UUID customerId);
    void deleteCustomerById(UUID customerId);
}
