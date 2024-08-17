package com.config.homework.repository;

import com.config.homework.model.entities.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrderProductRepository extends JpaRepository<ProductOrder, UUID> {
    @Query("SELECT po.product.id FROM products_orders po WHERE po.order.id = :orderId")
    List<UUID> findByOrderId(UUID orderId);
}
