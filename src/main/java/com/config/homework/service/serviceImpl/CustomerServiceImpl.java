package com.config.homework.service.serviceImpl;

import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerResponse;
import com.config.homework.model.entities.Customer;
import com.config.homework.model.entities.Email;
import com.config.homework.repository.CustomerRepository;
import com.config.homework.repository.EmailRepository;
import com.config.homework.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;
    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Email email = emailRepository.save(customerRequest.toEmailEntity(customerRequest.getEmail()));
        return customerRepository.save(customerRequest.toCustomerEntity(email)).toResponse();
    }
}
