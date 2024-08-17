package com.config.homework.model.dto.request;

import com.config.homework.model.entities.Customer;
import com.config.homework.model.entities.Order;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderRequest {
    private LocalDate orderDate = LocalDate.now();
    private double totalAmount;
    private String status;
    private UUID customerId;

    public Order toEntity(Customer customer){
        return new Order(null,this.orderDate,this.totalAmount,this.status,customer,null);
    }
    public Order toEntity(UUID orderId,Customer customer){
        return new Order(orderId,this.orderDate,this.totalAmount,this.status,customer,null);
    }
}
