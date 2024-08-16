package com.config.homework.service;

import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerResponse;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getAllCustomers(int size, int page, String sortBy, Sort.Direction sortDirection);
    CustomerResponse updateCustomerById(UUID customerId,CustomerRequest customerRequest);
    CustomerResponse getCustomerById(UUID customerId);
}
