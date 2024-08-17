package com.config.homework.model.entities;

import com.config.homework.model.dto.response.OrderResponse;
import com.config.homework.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;
    @Column(nullable = false)
    private double totalAmount;
    @Column(nullable = false)
    private String status;
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Customer customer;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductOrder> productOrders;

    public OrderResponse toResponse(Set<ProductResponse> productOrders) {
        return new OrderResponse(this.id,this.orderDate,this.totalAmount,this.status,productOrders);
    }
}
