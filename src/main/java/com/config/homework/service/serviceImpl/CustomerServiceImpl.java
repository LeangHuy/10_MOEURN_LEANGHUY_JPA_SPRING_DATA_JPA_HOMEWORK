package com.config.homework.service.serviceImpl;

import com.config.homework.exception.ConflictException;
import com.config.homework.exception.NotFoundException;
import com.config.homework.model.dto.request.CustomerRequest;
import com.config.homework.model.dto.response.CustomerOrderResponse;
import com.config.homework.model.dto.response.CustomerResponse;
import com.config.homework.model.dto.response.OrderResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;
    private final OrderServiceImpl orderServiceImpl;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Boolean unitEmail = emailRepository.existsByEmail(customerRequest.getEmail());
        if (unitEmail) {
            throw new ConflictException("Email already exists");
        }
        return customerRepository.save(customerRequest.toCustomerEntity(customerRequest.getEmail())).toResponse();
    }

    @Override
    public List<CustomerOrderResponse> getAllCustomers(int size, int page, String sortBy, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortBy.toLowerCase());
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerOrderResponse> customerResponses = new ArrayList<>();

        for (Customer customer : customers) {
            Set<OrderResponse> orderResponses = orderServiceImpl.getAllOrdersByCustomerId(customer.getId());
            CustomerOrderResponse customerResponse = customer.toOrderResponse(orderResponses);
            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    @Override
    public CustomerOrderResponse getCustomerById(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + customerId));
        Set<OrderResponse> orderResponses = orderServiceImpl.getAllOrdersByCustomerId(customer.getId());
        return customer.toOrderResponse(orderResponses);
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerOrderResponse updateCustomerById(UUID customerId, CustomerRequest customerRequest) {
        CustomerOrderResponse customerResponse = getCustomerById(customerId);
        return customerRepository.save(customerRequest.toCustomerEntity(
                        customerId, customerRequest.getEmail(), customerResponse.getEmail().getId()))
                .toOrderResponse(customerResponse.getOrderList());
    }


}
