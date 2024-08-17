package com.config.homework.repository;

import com.config.homework.model.dto.response.OrderResponse;
import com.config.homework.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM orders o WHERE o.customer.id = :customerId")
    Set<Order> findByCustomerId(UUID customerId);
}
