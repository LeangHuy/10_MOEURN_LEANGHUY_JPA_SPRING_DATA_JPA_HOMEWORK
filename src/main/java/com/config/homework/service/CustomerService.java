package com.config.homework.service;

import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
}
