package com.config.homework.service.serviceImpl;

import com.config.homework.exception.ConflictException;
import com.config.homework.exception.NotFoundException;
import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerResponse;
import com.config.homework.model.entities.Customer;
import com.config.homework.repository.CustomerRepository;
import com.config.homework.repository.EmailRepository;
import com.config.homework.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Boolean unitEmail = emailRepository.existsByEmail(customerRequest.getEmail());
        if (unitEmail) {
            throw new ConflictException("Email already exists");
        }
        return customerRepository.save(customerRequest.toCustomerEntity(customerRequest.getEmail())).toResponse();
    }

    @Override
    public List<CustomerResponse> getAllCustomers(int size, int page, String sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.toLowerCase());
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.getContent().stream().map(Customer::toResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + customerId))
                .toResponse();
    }

    @Override
    public CustomerResponse updateCustomerById(UUID customerId,CustomerRequest customerRequest) {
        CustomerResponse customerResponse = getCustomerById(customerId);
        return customerRepository.save(customerRequest.toCustomerEntity(customerId,customerRequest.getEmail(),customerResponse.getEmail().getId())).toResponse();
    }




}
